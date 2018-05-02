package ru.kpfu.itis.artgallery.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kpfu.itis.artgallery.models.File;
import ru.kpfu.itis.artgallery.models.FileUpload;
import ru.kpfu.itis.artgallery.models.User;
import ru.kpfu.itis.artgallery.repositories.FileRepository;
import ru.kpfu.itis.artgallery.services.AuthenticationService;
import ru.kpfu.itis.artgallery.validators.PhotoUploadValidator;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/")
public class FileUploadController {
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private AuthenticationService authenticationService;
    @Value("${CLOUDINARY_URL}")
    private String CLOUDINARY_URL;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String listPhotos(ModelMap model) {
        model.addAttribute("photos", fileRepository.findAll());
        return "photos";
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadPhoto(@ModelAttribute FileUpload fileUpload, BindingResult result,
                              ModelMap model, Authentication authentication) throws IOException {
        PhotoUploadValidator validator = new PhotoUploadValidator();
        validator.validate(fileUpload, result);

        Map uploadResult = null;
        if (fileUpload.getFile() != null && !fileUpload.getFile().isEmpty()) {
            Cloudinary cloudinary = new Cloudinary(CLOUDINARY_URL);
            uploadResult = cloudinary.uploader().upload(fileUpload.getFile().getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));
//            cloudinary.url().generate(user.getName() + "/" + )
            fileUpload.setPublicId((String) uploadResult.get("public_id"));
            Object version = uploadResult.get("version");
            if (version instanceof Integer) {
                fileUpload.setVersion(new Long((Integer) version));
            } else {
                fileUpload.setVersion((Long) version);
            }

            fileUpload.setSignature((String) uploadResult.get("signature"));
            System.out.println(uploadResult.get("signature"));
            fileUpload.setFormat((String) uploadResult.get("format"));
            System.out.println(uploadResult.get("format"));
            fileUpload.setResourceType((String) uploadResult.get("resource_type"));
            System.out.println(uploadResult.get("resource_type"));
        }

        if (result.hasErrors()) {
            model.addAttribute("fileUpload", fileUpload);
            return "redirect:/direct_upload_form";
        } else {
            User user = authenticationService.getUserByAuthentication(authentication);
            File file = new File();
            file.setName(fileUpload.getName());
            file.setContentType((String) uploadResult.getOrDefault("resource_type", "raw"));
            file.setUser(user);
            file.setUpload(fileUpload);
            model.addAttribute("upload", uploadResult);
            fileRepository.save(file);
            model.addAttribute("file", file);
            return "/";
        }
    }

    @RequestMapping(value = "/direct_upload_form", method = RequestMethod.GET)
    public String directUploadPhotoForm(ModelMap model) {
        model.addAttribute("photoUpload", new FileUpload());
        return "direct_upload_form";
    }
}

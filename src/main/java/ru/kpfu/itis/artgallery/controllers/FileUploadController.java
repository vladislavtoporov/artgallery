package ru.kpfu.itis.artgallery.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.artgallery.models.Exhibit;
import ru.kpfu.itis.artgallery.models.File;
import ru.kpfu.itis.artgallery.models.FileUpload;
import ru.kpfu.itis.artgallery.models.User;
import ru.kpfu.itis.artgallery.repositories.ExhibitRepository;
import ru.kpfu.itis.artgallery.repositories.FileRepository;
import ru.kpfu.itis.artgallery.services.AuthenticationService;
import ru.kpfu.itis.artgallery.validators.FileUploadValidator;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/")
public class FileUploadController {
    private FileRepository fileRepository;
    private ExhibitRepository exhibitRepository;
    private AuthenticationService authenticationService;
    @Value("${CLOUDINARY_URL}")
    private String CLOUDINARY_URL;
    private FileUploadValidator fileUploadValidator;

    public FileUploadController(FileRepository fileRepository, ExhibitRepository exhibitRepository, AuthenticationService authenticationService, FileUploadValidator fileUploadValidator) {
        this.fileRepository = fileRepository;
        this.exhibitRepository = exhibitRepository;
        this.authenticationService = authenticationService;
        this.fileUploadValidator = fileUploadValidator;
    }

    @InitBinder("fileForm")
    public void initFileUploadValidator(WebDataBinder binder) {
        binder.addValidators(fileUploadValidator);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadPhoto(@Valid @ModelAttribute("fileForm") FileUpload fileUpload, BindingResult result,
                              ModelMap model, Authentication authentication) throws IOException {
        FileUploadValidator validator = new FileUploadValidator();
        validator.validate(fileUpload, result);

        Map uploadResult = null;
        if (fileUpload.getFile() != null && !fileUpload.getFile().isEmpty()) {
            Cloudinary cloudinary = new Cloudinary(CLOUDINARY_URL);
            uploadResult = cloudinary.uploader().upload(fileUpload.getFile().getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));
            fileUpload.setPublicId((String) uploadResult.get("public_id"));
            Object version = uploadResult.get("version");
            if (version instanceof Integer) {
                fileUpload.setVersion(new Long((Integer) version));
            } else {
                fileUpload.setVersion((Long) version);
            }

//            fileUpload.setSignature((String) uploadResult.get("signature"));
//            System.out.println(uploadResult.get("signature"));
            fileUpload.setFormat((String) uploadResult.get("format"));
            fileUpload.setResourceType((String) uploadResult.get("resource_type"));
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
            file.setFormat(fileUpload.getFormat());
            file.setUpload(fileUpload);
            if (fileUpload.getExhibitId() != null) {
                Exhibit exhibit = exhibitRepository.getOne(fileUpload.getExhibitId());
                file.setExhibit(exhibit);
                if (exhibit.getPicture() == null && "image".equals(uploadResult.get("resource_type"))) {
                    exhibit.setPictureFile(fileUpload);
                }

            }
//            model.addAttribute("upload", uploadResult);
//            fileRepository.save(file);
//            model.addAttribute("file", file);
            return "/";
        }
    }

    @RequestMapping(value = "/direct_upload_form", method = RequestMethod.GET)
    public String directUploadPhotoForm(ModelMap model) {
        model.addAttribute("photoUpload", new FileUpload());
        return "direct_upload_form";
    }
}

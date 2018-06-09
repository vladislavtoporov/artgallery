package ru.kpfu.itis.artgallery.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kpfu.itis.artgallery.models.Exhibit;
import ru.kpfu.itis.artgallery.models.File;
import ru.kpfu.itis.artgallery.models.FileUpload;
import ru.kpfu.itis.artgallery.models.User;
import ru.kpfu.itis.artgallery.repositories.ExhibitRepository;
import ru.kpfu.itis.artgallery.repositories.FileRepository;
import ru.kpfu.itis.artgallery.repositories.UserRepository;
import ru.kpfu.itis.artgallery.services.AuthenticationService;
import ru.kpfu.itis.artgallery.validators.FileUploadValidator;

import java.io.IOException;
import java.util.Map;

@Controller
public class FileUploadController {
    private FileRepository fileRepository;
    private ExhibitRepository exhibitRepository;
    private AuthenticationService authenticationService;
    @Value("${CLOUDINARY_URL}")
    private String CLOUDINARY_URL;
    private FileUploadValidator validator;
    private UserRepository userRepository;

    @Autowired
    public FileUploadController(FileRepository fileRepository, ExhibitRepository exhibitRepository, AuthenticationService authenticationService, FileUploadValidator validator, UserRepository userRepository) {
        this.fileRepository = fileRepository;
        this.exhibitRepository = exhibitRepository;
        this.authenticationService = authenticationService;
        this.validator = validator;
        this.userRepository = userRepository;
    }

    @SuppressWarnings("rawtypes")
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadPhoto(@ModelAttribute FileUpload fileUpload, BindingResult result,
                              Authentication authentication) throws IOException {
        validator.validate(fileUpload, result);
        System.out.println("I am here");
        Map uploadResult = null;
        if (fileUpload.getFile() != null && !fileUpload.getFile().isEmpty()) {
            System.out.println("fileUpload");
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

            fileUpload.setFormat((String) uploadResult.get("format"));
            fileUpload.setResourceType((String) uploadResult.get("resource_type"));
            System.out.println(uploadResult.get("resource_type"));
        }

        if (result.hasErrors()) {
            System.out.println(result.getAllErrors().get(0).getDefaultMessage());
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
            fileRepository.save(file);
        }
        return "";
    }
}

package ru.kpfu.itis.artgallery.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.kpfu.itis.artgallery.models.FileUpload;

@Component
public class FileUploadValidator implements Validator {
    public boolean supports(Class clazz) {
        return FileUpload.class.equals(clazz);
    }

    public void validate(Object obj, Errors e) {
        ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
        FileUpload pu = (FileUpload) obj;
        if (pu.getFile() == null || pu.getFile().isEmpty()) {
            if (!pu.validSignature()) {
                e.rejectValue("signature", "signature.mismatch");
            }
        }
    }

}

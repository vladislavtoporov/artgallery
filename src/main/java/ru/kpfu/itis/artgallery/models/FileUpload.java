package ru.kpfu.itis.artgallery.models;

import com.cloudinary.Singleton;
import com.cloudinary.StoredFile;
import com.cloudinary.Transformation;
import org.springframework.web.multipart.MultipartFile;

public class FileUpload extends StoredFile {
    private String name;
    private Long exhibitId;
    private MultipartFile file;

    public Long getExhibitId() {
        return exhibitId;
    }

    public void setExhibitId(Long exhibitId) {
        this.exhibitId = exhibitId;
    }

    public String getUrl() {
        if (version != null && format != null && publicId != null) {
            return Singleton.getCloudinary().url()
                    .resourceType(resourceType)
                    .type(type)
                    .format(format)
                    .version(version)
                    .generate(publicId);
        } else return null;
    }

    public String getThumbnailUrl() {
        if (version != null && format != null && publicId != null) {
            return Singleton.getCloudinary().url().format(format)
                    .resourceType(resourceType)
                    .type(type)
                    .version(version).transformation(new Transformation().width(150).height(150).crop("fit"))
                    .generate(publicId);
        } else return null;
    }

    public String getComputedSignature() {
        return getComputedSignature(Singleton.getCloudinary());
    }

    public boolean validSignature() {
        return getComputedSignature().equals(signature);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}

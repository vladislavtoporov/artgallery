package ru.kpfu.itis.artgallery.utils;

import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.artgallery.models.File;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class FileStorageUtil {

    @Value("classpath:\\files")
    private String storagePath;

    public String getStoragePath() {
        return storagePath;
    }

    @SneakyThrows
    public void copyToStorage(MultipartFile file, String storageFileName) {
        Files.copy(file.getInputStream(), Paths.get(storagePath, storageFileName));
    }

    public File convertFromMultipart(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        String type = file.getContentType();
        long size = file.getSize();
        String storageName = createStorageName(originalFileName); // c расширением
        String fileUrl = getUrlOfFile(storageName); // полный путь
        return File.builder()
                .originName(storageName)
                .path(fileUrl)
                .build();
    }

    private String getUrlOfFile(String storageFileName) {
        return storagePath + "/" + storageFileName;
    }

    private String createStorageName(String originalFileName) {
        String extension = FilenameUtils.getExtension(originalFileName);
        String newFileName = UUID.randomUUID().toString();
        return newFileName + "." + extension;
    }
}

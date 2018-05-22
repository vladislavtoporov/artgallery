package ru.kpfu.itis.artgallery.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.artgallery.models.Exhibit;
import ru.kpfu.itis.artgallery.models.File;
import ru.kpfu.itis.artgallery.repositories.FileRepository;
import ru.kpfu.itis.artgallery.services.FileService;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;

    @Autowired
    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public List<File> findAllByExhibitAndContentTypeEquals(Exhibit exhibit, String contentType) {
        return fileRepository.findAllByExhibitAndContentTypeEquals(exhibit, contentType);
    }
}

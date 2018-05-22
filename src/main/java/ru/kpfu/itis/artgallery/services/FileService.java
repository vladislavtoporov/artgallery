package ru.kpfu.itis.artgallery.services;

import ru.kpfu.itis.artgallery.models.Exhibit;
import ru.kpfu.itis.artgallery.models.File;

import java.util.List;

public interface FileService {
    List<File> findAllByExhibitAndContentTypeEquals(Exhibit exhibit, String contentType);
}

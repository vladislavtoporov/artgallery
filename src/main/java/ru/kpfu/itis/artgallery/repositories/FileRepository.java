package ru.kpfu.itis.artgallery.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.artgallery.models.Exhibit;
import ru.kpfu.itis.artgallery.models.File;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {
    List<File> findAllByContentTypeEquals(Pageable pageable, String contentType);

    List<File> findAllByExhibitAndContentTypeEquals(Exhibit exhibit, String contentType);

}
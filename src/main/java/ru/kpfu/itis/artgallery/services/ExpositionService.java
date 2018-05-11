package ru.kpfu.itis.artgallery.services;

import org.springframework.data.domain.Page;
import ru.kpfu.itis.artgallery.models.Exposition;

public interface ExpositionService {

    Page<Exposition> findAllBySort(String querry, String sort, int page);
}

package ru.kpfu.itis.artgallery.services;

import org.springframework.data.domain.Page;
import ru.kpfu.itis.artgallery.models.Exposition;

public interface ExpositionService {

    Page<Exposition> findAllByQuerry(String querry, String filter, int page);

    Page<Exposition> findAll(int page);

    Page<Exposition> findSimilar(Exposition exposition);

    Exposition getOne(Long id);
}

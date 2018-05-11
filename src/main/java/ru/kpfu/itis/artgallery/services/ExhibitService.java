package ru.kpfu.itis.artgallery.services;

import org.springframework.data.domain.Page;
import ru.kpfu.itis.artgallery.models.Exhibit;
import ru.kpfu.itis.artgallery.models.Exposition;

import java.util.List;

public interface ExhibitService {
    Exhibit getOne(Long id);

    List<Exhibit> findSimilar(Exposition exposition);

    Page<Exhibit> findAllBySort(String querry, String sort, int page);
}

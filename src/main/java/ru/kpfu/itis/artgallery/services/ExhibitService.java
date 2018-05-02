package ru.kpfu.itis.artgallery.services;

import ru.kpfu.itis.artgallery.models.Exhibit;
import ru.kpfu.itis.artgallery.models.Exposition;

import java.util.List;

public interface ExhibitService {
    Exhibit getOne(Long id);

    List<Exhibit> findSimilar(Exposition exposition);
}

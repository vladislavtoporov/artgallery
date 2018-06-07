package ru.kpfu.itis.artgallery.services;

import org.springframework.data.domain.Page;
import ru.kpfu.itis.artgallery.models.Exhibit;

import java.util.Optional;

public interface ExhibitService {
    Exhibit getOne(Long id);

    Page<Exhibit> findAllSortByName(int page);

    Page<Exhibit> findAllByQuerry(String querry, String filter, int page);

    Page<Exhibit> findSimilar(Exhibit exhibit);

    Optional<Exhibit> findByName(String name);

    Page<Exhibit> findLast();
}

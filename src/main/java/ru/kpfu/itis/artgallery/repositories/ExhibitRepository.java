package ru.kpfu.itis.artgallery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.artgallery.models.Exhibit;
import ru.kpfu.itis.artgallery.models.Exposition;

import java.util.Set;

public interface ExhibitRepository extends JpaRepository<Exhibit, Long> {
    Set<Exhibit> findAllByExposition(Exposition exposition);
}

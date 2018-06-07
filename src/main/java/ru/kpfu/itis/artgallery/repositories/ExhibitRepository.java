package ru.kpfu.itis.artgallery.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.artgallery.models.Exhibit;
import ru.kpfu.itis.artgallery.models.Exposition;
import ru.kpfu.itis.artgallery.models.User;

import java.util.Optional;
import java.util.Set;

public interface ExhibitRepository extends JpaRepository<Exhibit, Long> {
    Set<Exhibit> findAllByExposition(Exposition exposition);

    Page<Exhibit> findAllByNameContainingOrderByTs(Pageable pageable, String querry);

    Page<Exhibit> findAllByNameContainingOrderByAuthor(Pageable pageable, String querry);

    Page<Exhibit> findAll(Pageable pageable);

    Page<Exhibit> findAllByAuthor(Pageable pageable, User author);

    Optional<Exhibit> findByName(String name);
}

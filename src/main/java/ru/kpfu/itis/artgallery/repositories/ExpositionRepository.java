package ru.kpfu.itis.artgallery.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.artgallery.models.Exposition;

public interface ExpositionRepository extends JpaRepository<Exposition, Long> {
    Page<Exposition> findAllByNameContainingOrderByPrice(Pageable pageable, String querry);

    Page<Exposition> findAllByNameContainingOrderByStart(Pageable pageable, String querry);

}

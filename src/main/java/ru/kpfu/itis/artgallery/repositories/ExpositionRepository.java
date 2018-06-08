package ru.kpfu.itis.artgallery.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.artgallery.models.Exposition;
import ru.kpfu.itis.artgallery.models.User;

import java.util.Date;
import java.util.List;

public interface ExpositionRepository extends JpaRepository<Exposition, Long> {
    Page<Exposition> findAllByNameContainingOrderByPrice(Pageable pageable, String querry);

    Page<Exposition> findAllByNameContainingOrderByStart(Pageable pageable, String querry);

    Page<Exposition> findAll(Pageable pageable);

    Page<Exposition> findAllByOwner(Pageable pageable, User owner);

    List<Exposition> findAllByFinishAfter(Date date);

    Page<Exposition> findAllByNameContainingOrderByOwner(Pageable pageRequest, String querry);
}

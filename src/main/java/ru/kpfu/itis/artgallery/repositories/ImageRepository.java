package ru.kpfu.itis.artgallery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.artgallery.models.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}

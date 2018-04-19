package ru.kpfu.itis.artgallery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.artgallery.models.News;

public interface NewsRepository extends JpaRepository<News, Long> {
}

package ru.kpfu.itis.artgallery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.artgallery.models.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
}

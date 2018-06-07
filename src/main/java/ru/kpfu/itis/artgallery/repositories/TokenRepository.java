package ru.kpfu.itis.artgallery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.artgallery.models.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Token findByValue(String token);
}

package ru.kpfu.itis.artgallery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.artgallery.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByLogin(String login);
//    Optional<User> findOneByEmail(String email);
}

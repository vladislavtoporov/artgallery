package ru.kpfu.itis.artgallery.services;


import ru.kpfu.itis.artgallery.models.User;

import java.util.Optional;

public interface TokenService {
    Optional<User> loadUserByToken(String token);
}
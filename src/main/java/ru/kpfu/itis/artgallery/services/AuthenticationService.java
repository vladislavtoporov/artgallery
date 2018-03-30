package ru.kpfu.itis.artgallery.services;

import org.springframework.security.core.Authentication;
import ru.kpfu.itis.artgallery.models.User;

public interface AuthenticationService {
    User getUserByAuthentication(Authentication authentication);
}

package ru.kpfu.itis.artgallery.services.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.artgallery.details.UserDetailsImpl;
import ru.kpfu.itis.artgallery.models.User;
import ru.kpfu.itis.artgallery.repositories.UserRepository;
import ru.kpfu.itis.artgallery.services.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private UserRepository userRepository;

    public AuthenticationServiceImpl(UserRepository usersRepository) {
        this.userRepository = usersRepository;
    }

    @Override
    public User getUserByAuthentication(Authentication authentication) {
        UserDetailsImpl currentUserDetails = (UserDetailsImpl) authentication.getPrincipal();
//        ExampleUserDetails currentUserDetails = (ExampleUserDetails) authentication.getPrincipal();
        User currentUserModel = currentUserDetails.getUser();
        Long currentUserId = currentUserModel.getId();
        return userRepository.getOne(currentUserId);
    }
}

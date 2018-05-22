package ru.kpfu.itis.artgallery.config.security;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.artgallery.models.User;
import ru.kpfu.itis.artgallery.repositories.UserRepository;
import ru.kpfu.itis.artgallery.utils.PasswordGenerator;

@Component
public class SocialConnectionSignup implements ConnectionSignUp {

    private UserRepository userRepository;

    public SocialConnectionSignup(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String execute(Connection<?> connection) {
        System.out.println("signup === ");
        final User user = new User();
        user.setName(connection.getDisplayName());
        user.setLogin(connection.fetchUserProfile().getEmail());
        user.setAvatarPath(connection.getImageUrl());
        user.setPassword(new PasswordGenerator().generate());
        userRepository.save(user);
        return user.getName();
    }

}

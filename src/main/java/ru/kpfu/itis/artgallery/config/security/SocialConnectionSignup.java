package ru.kpfu.itis.artgallery.config.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.artgallery.details.UserDetailsImpl;
import ru.kpfu.itis.artgallery.models.User;
import ru.kpfu.itis.artgallery.repositories.UserRepository;
import ru.kpfu.itis.artgallery.utils.PasswordGenerator;

import java.util.Arrays;

@Component
public class SocialConnectionSignup implements ConnectionSignUp {

    private UserRepository userRepository;

    public SocialConnectionSignup(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String execute(Connection<?> connection) {
        User user = new User();
        user.setName(connection.getDisplayName());
        user.setLogin(connection.fetchUserProfile().getEmail());
        user.setAvatarPath(connection.getImageUrl());
        user.setPassword(new PasswordGenerator().generate());
        if (!userRepository.findOneByLogin(user.getLogin()).isPresent()) {
            userRepository.save(user);
        }
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(new UserDetailsImpl(user), user.getPassword(), Arrays.asList(new SimpleGrantedAuthority("USER"))));
        return user.getLogin();
    }

}

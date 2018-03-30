package ru.kpfu.itis.artgallery.services.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.artgallery.enums.Role;
import ru.kpfu.itis.artgallery.forms.UserRegistrationForm;
import ru.kpfu.itis.artgallery.models.User;
import ru.kpfu.itis.artgallery.repositories.UserRepository;
import ru.kpfu.itis.artgallery.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public void register(UserRegistrationForm userForm) {
        User newUser = User.builder()
                .login(userForm.getLogin())
                .name(userForm.getLogin())
                .email(userForm.getEmail())
                .password(passwordEncoder.encode(userForm.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(newUser);
    }
}

package ru.kpfu.itis.artgallery.services;

import ru.kpfu.itis.artgallery.forms.UserRegistrationForm;

public interface UserService {
    void register(UserRegistrationForm userForm);
}

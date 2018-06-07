package ru.kpfu.itis.artgallery.services;


import ru.kpfu.itis.artgallery.forms.LoginForm;
import ru.kpfu.itis.artgallery.forms.TokenDto;

public interface LoginService {
    TokenDto login(LoginForm loginPassword, boolean isJwtEnabled);
}

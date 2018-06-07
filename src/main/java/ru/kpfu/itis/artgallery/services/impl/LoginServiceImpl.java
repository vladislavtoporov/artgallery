package ru.kpfu.itis.artgallery.services.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.artgallery.forms.LoginForm;
import ru.kpfu.itis.artgallery.forms.TokenDto;
import ru.kpfu.itis.artgallery.models.Token;
import ru.kpfu.itis.artgallery.models.User;
import ru.kpfu.itis.artgallery.repositories.TokenRepository;
import ru.kpfu.itis.artgallery.repositories.UserRepository;
import ru.kpfu.itis.artgallery.services.LoginService;

import java.time.LocalDate;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository usersRepository;

    @Autowired
    private TokenRepository tokensRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Override
    public TokenDto login(LoginForm loginPassword, boolean isJwtEnabled) {
        String rawPassword = loginPassword.getPassword();
        String login = loginPassword.getLogin();

        User user = usersRepository.findOneByLogin(login).orElseThrow(()
                -> new IllegalArgumentException("User not found by login <" + login + ">"));

        if (encoder.matches(rawPassword, user.getPassword())) {
            if (!isJwtEnabled) {
                String token = RandomStringUtils.random(15, true, true);
                Token tokenModel = Token.builder()
                        .user(user)
                        .expiredDate(LocalDate.now().plusDays(2))
                        .value(token)
                        .build();
                tokensRepository.save(tokenModel);
                return TokenDto.builder()
                        .value(token)
                        .userLogin(tokenModel.getUser().getLogin())
                        .build();
            } else {
                return TokenDto.builder().value(Jwts.builder()
                        .claim("role", user.getRole().toString())
                        .claim("login", user.getLogin())
                        .setSubject(user.getId().toString())
                        .signWith(SignatureAlgorithm.HS512, jwtSecret).compact())
                        .userLogin(user.getLogin())
                        .build();
            }
        } else
            throw new IllegalArgumentException("User login/password incorrect");
    }
}
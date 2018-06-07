package ru.kpfu.itis.artgallery.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.artgallery.models.Token;
import ru.kpfu.itis.artgallery.models.User;
import ru.kpfu.itis.artgallery.repositories.TokenRepository;
import ru.kpfu.itis.artgallery.services.TokenService;

import java.util.Optional;

@Service
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokensRepository;

    @Autowired
    public TokenServiceImpl(TokenRepository tokensRepository) {
        this.tokensRepository = tokensRepository;
    }

    @Override
    public Optional<User> loadUserByToken(String token) {
        Token model = tokensRepository.findByValue(token);
        if (model == null) {
            return Optional.empty();
        }
        return Optional.of(model.getUser());
    }
}

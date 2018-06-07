package ru.kpfu.itis.artgallery.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.artgallery.authentication.TokenAuthentication;
import ru.kpfu.itis.artgallery.models.User;
import ru.kpfu.itis.artgallery.services.TokenService;

import java.util.Optional;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    private TokenService service;

    private UserDetailsService userDetailsService;

    @Autowired
    public TokenAuthenticationProvider(TokenService service, UserDetailsService userDetailsService) {
        this.service = service;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        TokenAuthentication tokenAuthentication = (TokenAuthentication) authentication;
        Optional<User> candidateUser = service.loadUserByToken(tokenAuthentication.getName());
        if (candidateUser.isPresent()) {
            User user = candidateUser.get();
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getLogin());
            tokenAuthentication.setUserDetails(userDetails);
            tokenAuthentication.setAuthenticated(true);
            return tokenAuthentication;
        } else {
            throw new AuthenticationServiceException("User not found");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.equals(authentication);
    }
}
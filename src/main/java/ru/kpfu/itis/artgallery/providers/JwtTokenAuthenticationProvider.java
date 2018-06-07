package ru.kpfu.itis.artgallery.providers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.artgallery.authentication.JwtTokenAuthentication;
import ru.kpfu.itis.artgallery.details.UserDetailsImpl;
import ru.kpfu.itis.artgallery.enums.Role;
import ru.kpfu.itis.artgallery.models.User;

@Component
public class JwtTokenAuthenticationProvider implements AuthenticationProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtTokenAuthentication tokenAuthentication = (JwtTokenAuthentication) authentication;

        Claims body;
        try {
            body = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(tokenAuthentication.getName())
                    .getBody();
        } catch (MalformedJwtException | SignatureException e) {
            e.printStackTrace();
            throw new AuthenticationServiceException("Invalid token");
        }
        User user = User.builder()
                .id(Long.parseLong(body.get("sub").toString()))
                .login(body.get("login").toString())
                .role(Role.valueOf(body.get("role").toString()))
                .build();
        UserDetails userDetails = new UserDetailsImpl(user);

        tokenAuthentication.setUserDetails(userDetails);
        tokenAuthentication.setAuthenticated(true);
        return tokenAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtTokenAuthentication.class.equals(authentication);
    }
}

package ru.kpfu.itis.artgallery.config.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Arrays;

@Component
public class SocialSignInAdapter implements SignInAdapter {
    @Override
    public String signIn(String localUserId, Connection<?> connection, NativeWebRequest request) {
        System.out.println(" ====== Sign In adapter");
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(connection.getDisplayName(), null, Arrays.asList(new SimpleGrantedAuthority("USER"))));
        return null;
    }
}

package ru.kpfu.itis.artgallery.providers;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.artgallery.repositories.UserRepository;

import java.util.Collection;

@Component
public class AuthProvider implements AuthenticationProvider {

    private UserRepository usersRepository;
    private UserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;

    public AuthProvider(UserRepository usersRepository, UserDetailsService userDetailsService) {
        this.usersRepository = usersRepository;
        this.userDetailsService = userDetailsService;
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();

//        Optional<User> userOptional = usersRepository.findOneByLogin(username);
//        // если пользователь найден
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            // если пароль пользователя не совпал с тем, который ввели
//            if (!encoder.matches(password, user.getHashPassword())) {
//                // проверяем, может заходит админ?
//                if (encoder.matches(password, user.getHashTempPassword())) {
//                    // если все нормально - обнуляем этот временный пароль пользователю
//                    user.setHashTempPassword(null);
//                    usersRepository.save(user);
//                } else {
//                    throw new BadCredentialsException("Wrong password or login");
//                }
//            }
//        } else {
//            throw new BadCredentialsException("Wrong password or login");
//        }

        // сюда попадем, если заходит либо пользователь с нормальным паролем
        // либо админ с временным


        UserDetails details = userDetailsService.loadUserByUsername(login);
        Collection<? extends GrantedAuthority> authorities = details.getAuthorities();
        return new UsernamePasswordAuthenticationToken(details, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}

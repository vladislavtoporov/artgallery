package ru.kpfu.itis.artgallery.details;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.artgallery.models.User;
import ru.kpfu.itis.artgallery.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository usersRepository;

    public UserDetailsServiceImpl(UserRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = usersRepository.findOneByLogin(login).orElseThrow(()
                -> new IllegalArgumentException("User not found by login <" + login + ">"));
        return new UserDetailsImpl(user);
    }
}

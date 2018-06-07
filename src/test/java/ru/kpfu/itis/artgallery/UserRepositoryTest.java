package ru.kpfu.itis.artgallery;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kpfu.itis.artgallery.config.Application;
import ru.kpfu.itis.artgallery.forms.UserRegistrationForm;
import ru.kpfu.itis.artgallery.models.User;
import ru.kpfu.itis.artgallery.repositories.UserRepository;
import ru.kpfu.itis.artgallery.services.UserService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRepositoryTest {

    @MockBean
    private UserRepository userRepository;
    private User user;

    @MockBean
    private UserService userService;

    @MockBean
    private PasswordEncoder passwordEncoderMock;
    private String email;
    private String password;


    @Before
    public void setUp() {
        email = "blabla@bla.com";
        password = "BLalala1";
        UserRegistrationForm userForm = UserRegistrationForm.builder()
                .login(email)
                .password(password)
                .passwordRepeat(password)
                .name(email)
                .build();
        user = User.builder()
                .login(email)
                .password(password)
                .name(email)
                .build();
    }

    @Test
    public void whenValidNameThenReturnUser() {
        Mockito.when(userRepository.findOneByLogin(user.getLogin()))
                .thenReturn(Optional.of(user));
        Optional<User> optional = userRepository.findOneByLogin(user.getLogin());
        Assert.assertTrue(optional.isPresent());
    }

    @Test
    public void registerNewUserShouldCreateNewUserAccount() {

        when(userRepository.findOneByLogin(email)).thenReturn(Optional.empty()).thenReturn(Optional.of(user));
        when(userRepository.save(isA(User.class))).thenAnswer((Answer<User>) invocation -> {
            Object[] arguments = invocation.getArguments();
            return (User) arguments[0];
        });
//
//        userService.register(userForm);
//


        verify(userRepository).findOneByLogin(email);
        User user = userRepository.save(this.user);
        assertThat(user).hasFieldOrPropertyWithValue("email", email)
                .hasFieldOrPropertyWithValue("password", password)
                .hasFieldOrPropertyWithValue("name", email);

//        verify(userRepository, times(1)).save(user);
        verify(userRepository).findOneByLogin(email);
//        verifyNoMoreInteractions(userRepository);
//        verifyZeroInteractions(passwordEncoderMock);
    }
}

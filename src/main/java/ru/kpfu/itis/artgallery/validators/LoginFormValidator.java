package ru.kpfu.itis.artgallery.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.kpfu.itis.artgallery.forms.LoginForm;
import ru.kpfu.itis.artgallery.forms.UserRegistrationForm;
import ru.kpfu.itis.artgallery.models.User;
import ru.kpfu.itis.artgallery.repositories.UserRepository;

import java.util.Optional;

@Component
public class LoginFormValidator implements Validator {

    private UserRepository usersRepository;

    public LoginFormValidator(UserRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.getName().equals(UserRegistrationForm.class.getName());
    }

    @Override
    public void validate(Object target, Errors errors) {
        LoginForm form = (LoginForm) target;
        Optional<User> existedUser = usersRepository.findOneByLogin(form.getLogin());

        if (!existedUser.isPresent()) {
            errors.reject("bad.login", "Неправильный логин или пароль");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "empty.login", "Пустой email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "empty.password", "Пустой пароль");
    }
}

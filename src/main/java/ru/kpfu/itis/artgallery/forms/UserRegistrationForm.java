package ru.kpfu.itis.artgallery.forms;

import lombok.*;
import org.hibernate.validator.constraints.Email;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegistrationForm {
    @Email(message = "логин должен содержать email")
    private String login;
    private String name;
    private String password;
    private String passwordRepeat;
}

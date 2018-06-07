package ru.kpfu.itis.artgallery.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
    @NotEmpty(message = "роль не должна быть пустой")
    private String role;

}

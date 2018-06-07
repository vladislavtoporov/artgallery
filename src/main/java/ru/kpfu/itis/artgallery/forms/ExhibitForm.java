package ru.kpfu.itis.artgallery.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ExhibitForm {
    private Long id;
    @NotEmpty(message = "название не должно быть пустым")
    private String name;
    @NotEmpty(message = "описание не должно быть пустым")
    private String content;
    private Long expositionId;
    private Long userId;

}

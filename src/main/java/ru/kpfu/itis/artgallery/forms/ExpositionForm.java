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

public class ExpositionForm {
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotEmpty
    private String start;
    @NotEmpty
    private String finish;

}

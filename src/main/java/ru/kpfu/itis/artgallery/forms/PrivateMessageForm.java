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

public class PrivateMessageForm {
    @NotEmpty(message = "получатель не должен быть пустым")
    private String recipient;
    @NotEmpty(message = "контент не должно быть пустым")
    private String content;
    @NotEmpty(message = "тема не должна быть пустой")
    private String header;
}

package ru.kpfu.itis.artgallery.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ExhibitForm {
    private Long id;
    private String name;
    private String content;
    private String audioPath;
    private String videoPath;
    private Long expositionId;

}

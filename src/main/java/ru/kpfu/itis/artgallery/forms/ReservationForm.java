package ru.kpfu.itis.artgallery.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationForm {
    @NotEmpty(message = "имя клиента не должно быть пустым")
    private String customer;
    @Range(min = 0)
    private Integer numberOfVisitors;
    @NotEmpty(message = "город не должен быть пустым")
    private String city;
    @NotEmpty(message = "дата не должна быть пустой")
    private String date;
    @NotEmpty(message = "время не должно быть пустым")
    private String time;
    @NotEmpty(message = "название экспозиции не должно быть пустым")
    private String exposition;
}

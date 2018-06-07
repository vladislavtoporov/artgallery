package ru.kpfu.itis.artgallery.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;
import ru.kpfu.itis.artgallery.forms.ExpositionForm;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = {"exhibits", "usersAccess"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@DynamicInsert
@DynamicUpdate
public class Exposition {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exposition_id_seq")
    @SequenceGenerator(name = "exposition_id_seq", sequenceName = "exposition_id_seq", allocationSize = 1)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer price;

    private String picture;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date finish;

    @JsonManagedReference
    @OneToMany(mappedBy = "exposition")
    private Set<Exhibit> exhibits;

    @JsonIgnore
    @ManyToMany(targetEntity = User.class, mappedBy = "expositionsAccess")
    private Set<User> usersAccess;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    public Exposition(ExpositionForm expositionForm, User owner) {
        this.name = expositionForm.getName();
        this.description = expositionForm.getDescription();
        this.owner = owner;
        String[] startArray = expositionForm.getStart().split("\\.");
        int[] startValue = Arrays.stream(startArray).mapToInt(Integer::parseInt).toArray();
        String[] finishArray = expositionForm.getFinish().split("\\.");
        int[] finishValue = Arrays.stream(finishArray).mapToInt(Integer::parseInt).toArray();

        this.start = Date.valueOf(LocalDate.of(startValue[0], startValue[1], startValue[2]));
        this.finish = Date.valueOf(LocalDate.of(finishValue[0], finishValue[1], finishValue[2]));
    }

    public String getRange() {
        if (start != null && finish != null)
            return start.toString() + " - " + finish.toString();
        else return "";
    }
}

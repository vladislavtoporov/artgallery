package ru.kpfu.itis.artgallery.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import ru.kpfu.itis.artgallery.forms.ExpositionForm;

import javax.persistence.*;
import java.sql.Timestamp;
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

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp start;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp finish;

    @OneToMany(mappedBy = "exposition")
    private Set<Exhibit> exhibits;


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
    }
}

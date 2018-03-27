package ru.kpfu.itis.artgallery.models;

import lombok.*;

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
public class Exposition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    private String description;

    @Column(columnDefinition="INT DEFAULT 0")
    private Integer price;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp start;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp finish;

    @OneToMany(mappedBy = "exposition")
    private Set<Exhibit> exhibits;



    @ManyToMany(targetEntity = User.class, mappedBy = "expositionsAccess")
    private Set<User> usersAccess;

}

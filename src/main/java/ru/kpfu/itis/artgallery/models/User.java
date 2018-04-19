package ru.kpfu.itis.artgallery.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import ru.kpfu.itis.artgallery.enums.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = {"expositionsAccess"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
    private Long id;
    @Column(length = 50, nullable = false)
    private String name;

//    @Column(length = 50)
//    private String login;

    @Email
    @JsonIgnore
    @Column(unique = true, length = 50, nullable = false)
    private String login;


    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @JsonIgnore
    @Column(name = "avatar_path")
    private String avatarPath;

    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonIgnore
    @ManyToMany(targetEntity = Exposition.class, cascade = CascadeType.ALL)
    @JoinTable(name = "user_exposition",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "exposition_id", referencedColumnName = "id"))
    private Set<Exposition> expositionsAccess;

}

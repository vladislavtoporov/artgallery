package ru.kpfu.itis.artgallery.models;

import lombok.*;
import ru.kpfu.itis.artgallery.enums.Role;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = {"expositionsAccess"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50)
    private String username;

    @Column(unique = true, length = 50, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "avatar_path")
    private String avatarPath;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(targetEntity = Exposition.class, cascade = CascadeType.ALL)
    @JoinTable(name = "user_exposition",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "exposition_id", referencedColumnName = "id"))
    private Set<Exposition> expositionsAccess;

}

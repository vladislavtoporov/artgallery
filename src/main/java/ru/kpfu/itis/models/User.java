package ru.kpfu.itis.models;

import lombok.*;

import javax.persistence.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @SequenceGenerator(name="users_id_seq", sequenceName="users_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "email", unique = true, length = 50)
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "avatar_path")
    private String avatarPath;

}

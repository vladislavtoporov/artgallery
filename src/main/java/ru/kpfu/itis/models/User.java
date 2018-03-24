package ru.kpfu.itis.models;

import lombok.*;

import javax.persistence.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @SequenceGenerator(name="users_id_seq", sequenceName="users_id_seq", allocationSize = 1)
    private Long id;

    @Column(unique = true, length = 50)
    private String email;

    private String password;

    private String avatarPath;

}

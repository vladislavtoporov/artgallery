package ru.kpfu.itis.artgallery.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = {"posts"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "topic")
    private Set<Post> posts;

}

package ru.kpfu.itis.artgallery.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "topic_id_seq")
    @SequenceGenerator(name = "topic_id_seq", sequenceName = "topic_id_seq", allocationSize = 1)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "topic")
    private Set<Post> posts;

}

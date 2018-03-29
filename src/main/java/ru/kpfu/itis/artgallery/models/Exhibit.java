package ru.kpfu.itis.artgallery.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = {"author", "images", "exposition"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Exhibit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 1000)
    private String content;

    @Column(name = "audio_path", length = 50)
    private String audioPath;

    @Column(name = "video_path", length = 50)
    private String videoPath;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp ts;

    @Column(name = "sum_votes", columnDefinition="INT DEFAULT 0")
    private Integer sumVotes;

    @Column(name = "count_votes", columnDefinition="INT DEFAULT 0")
    private Integer countVotes;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @JsonIgnore
    @OneToMany(mappedBy = "exhibit")
    private Set<Image> images;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exposition_id", nullable = false)
    private Exposition exposition;

}

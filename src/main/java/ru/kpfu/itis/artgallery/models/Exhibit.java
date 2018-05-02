package ru.kpfu.itis.artgallery.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import ru.kpfu.itis.artgallery.forms.ExhibitForm;

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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@DynamicInsert
@DynamicUpdate
public class Exhibit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exhibit_id_seq")
    @SequenceGenerator(name = "exhibit_id_seq", sequenceName = "exhibit_id_seq", allocationSize = 1)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 1000)
    private String content;

    @Column(name = "audio_path", length = 50)
    private String audioPath;

    @Column(name = "video_path", length = 50)
    private String videoPath;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp ts;

    @Column(name = "sum_votes", columnDefinition = "INT DEFAULT 0")
    private Integer sumVotes;

    @Column(name = "count_votes", columnDefinition = "INT DEFAULT 0")
    private Integer countVotes;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @JsonIgnore
    @OneToMany(mappedBy = "exhibit")
    private Set<File> images;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exposition_id", nullable = false)
    private Exposition exposition;

    public Exhibit(ExhibitForm exhibitForm, User author, Exposition exposition) {
        this.name = exhibitForm.getName();
        this.content = exhibitForm.getContent();
        this.exposition = exposition;
        this.author = author;
    }
}

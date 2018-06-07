package ru.kpfu.itis.artgallery.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@ToString(exclude = {"user"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@DynamicInsert
public class News {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User user;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "news_id_seq")
    @SequenceGenerator(name = "news_id_seq", sequenceName = "news_id_seq", allocationSize = 1)
    private Long id;
    @NotEmpty
    private String header;
    @NotEmpty
    private String preview;
    @Column(length = 3000)
    @NotEmpty
    private String content;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp ts;
}

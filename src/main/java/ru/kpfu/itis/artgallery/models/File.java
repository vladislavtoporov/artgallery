package ru.kpfu.itis.artgallery.models;

import com.cloudinary.StoredFile;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@ToString(exclude = {"message", "exhibit"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"user", "ticket", "message", "exhibit"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity()
@DynamicInsert
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    private String name;
    @Column(length = 10)
    private String contentType;

    @Basic
    private String file;


    private String format;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id")
    private PrivateMessage message;


    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exhibit_id")
    private Exhibit exhibit;

    @Basic
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp ts;

    public StoredFile getUpload() {
        StoredFile file = new StoredFile();
        file.setPreloadedFile(this.file);
        return file;
    }

    public void setUpload(StoredFile file) {
        this.file = file.getPreloadedFile();
    }

    public String getFullPath() {
        String hosting = "http://res.cloudinary.com/mt21/";
        return hosting + file;
    }

}
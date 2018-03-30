package ru.kpfu.itis.artgallery.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "persistent_logins")
public class PersistentLogin {
    @Id
    @Column(length = 64, nullable = false)
    String series;

    @Column(length = 64, nullable = false)
    String username;

    @Column(length = 64, nullable = false)
    String token;

    @Column(name = "last_used", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    Timestamp lastUsed;

}

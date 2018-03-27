package ru.kpfu.itis.artgallery.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString(exclude = {"exhibit"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "origin_name", length = 50)
    private String originName;

    @Column(length = 50)
    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exhibit_id", nullable = false)
    private Exhibit exhibit;

}

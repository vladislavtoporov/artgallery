package ru.kpfu.itis.artgallery.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import ru.kpfu.itis.artgallery.forms.PrivateMessageForm;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = {"recipient", "sender", "files"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "private_message")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@DynamicInsert
public class PrivateMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "private_message_id_seq")
    @SequenceGenerator(name = "private_message_id_seq", sequenceName = "private_message_id_seq", allocationSize = 1)
    private Long id;

    @Column(length = 500)
    private String content;

    @Column(length = 100)
    private String header;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp ts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id", nullable = false)
    private User recipient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @OneToMany(mappedBy = "message")
    private Set<File> files;

    public PrivateMessage(PrivateMessageForm privateMessageForm, User sender, User recipient) {
        this.setContent(privateMessageForm.getContent());
        this.setSender(sender);
        this.setRecipient(recipient);
        this.setHeader(privateMessageForm.getHeader());
    }
}

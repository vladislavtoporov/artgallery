package ru.kpfu.itis.artgallery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.artgallery.models.PrivateMessage;
import ru.kpfu.itis.artgallery.models.User;

import java.util.List;

public interface PrivateMessageRepository extends JpaRepository<PrivateMessage, Long> {
    List<PrivateMessage> findAllByRecipientAndIsRead(User user, Boolean flag);
    List<PrivateMessage> findAllByRecipient(User user);

    List<PrivateMessage> findAllBySender(User user);
}

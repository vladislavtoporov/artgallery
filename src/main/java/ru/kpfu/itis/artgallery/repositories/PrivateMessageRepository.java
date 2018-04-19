package ru.kpfu.itis.artgallery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.artgallery.models.PrivateMessage;

public interface PrivateMessageRepository extends JpaRepository<PrivateMessage, Long> {
}

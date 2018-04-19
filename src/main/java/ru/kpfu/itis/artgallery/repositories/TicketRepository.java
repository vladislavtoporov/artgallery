package ru.kpfu.itis.artgallery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.artgallery.models.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}

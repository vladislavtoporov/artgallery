package ru.kpfu.itis.artgallery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.artgallery.enums.TicketStatus;
import ru.kpfu.itis.artgallery.models.Ticket;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllByTicketStatus(TicketStatus ticketStatus);
}

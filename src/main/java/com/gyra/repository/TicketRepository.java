package com.gyra.repository;

import com.gyra.model.ticket.Ticket;
import com.gyra.model.ticket.TicketPriority;
import com.gyra.model.ticket.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Long> countByStatus(TicketStatus ticketStatus);
    Optional<Long> countByPriority(TicketPriority ticketPriority);
}

package com.gyra.service.interfaces;

import com.gyra.model.ticket.Ticket;
import com.gyra.model.ticket.TicketDto;
import com.gyra.model.ticket.TicketPriority;
import com.gyra.model.ticket.TicketStatus;

import java.util.List;
import java.util.Map;

public interface TicketService {
    Map<TicketStatus, Long> getTicketCountByStatus();
    Map<TicketPriority, Long> getTicketCountByPriority();
    Ticket getTicketById(Long id);
    List<Ticket> getAllTickets();

    void addTicket(TicketDto ticketDto);
    void updateTicket(Long id, Ticket ticket);
    void deleteTicket(Long id);
}

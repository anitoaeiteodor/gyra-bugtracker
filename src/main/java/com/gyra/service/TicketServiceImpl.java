package com.gyra.service;

import com.gyra.model.ticket.Ticket;
import com.gyra.model.ticket.TicketDto;
import com.gyra.model.ticket.TicketPriority;
import com.gyra.model.ticket.TicketStatus;
import com.gyra.repository.TicketRepository;
import com.gyra.service.interfaces.TicketService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Override
    public Map<TicketStatus, Long> getTicketCountByStatus() {
        HashMap<TicketStatus, Long> ticketCount = new HashMap<>();
        for (TicketStatus status: TicketStatus.values()) {
            Optional<Long> count = ticketRepository.countByStatus(status);
            count.ifPresent(value -> ticketCount.put(status, value));
        }
        return ticketCount;
    }

    @Override
    public Map<TicketPriority, Long> getTicketCountByPriority() {
        HashMap<TicketPriority, Long> ticketCount = new HashMap<>();
        for (TicketPriority priority: TicketPriority.values()) {
            Optional<Long> count = ticketRepository.countByPriority(priority);
            count.ifPresent(value -> ticketCount.put(priority, value));
        }
        return ticketCount;
    }

    @Override
    @Transactional
    public void updateTicket(Long id, Ticket ticket) {
        Ticket oldTicket = ticketRepository.getById(id);
        oldTicket.setStatus(ticket.getStatus());
        oldTicket.setPriority(ticket.getPriority());
        oldTicket.setUser(ticket.getUser());
        oldTicket.setDescription(ticket.getDescription());
        oldTicket.setTitle(ticket.getTitle());
        ticketRepository.save(oldTicket);
    }

    @Override
    @Transactional
    public void deleteTicket(Long id) {
        log.info("Deleting ticket " + ticketRepository.getById(id));
        ticketRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addTicket(TicketDto ticketDto) {
        log.info("Adding ticket: " + ticketDto);

        Ticket ticket = new Ticket(ticketDto.getTitle(), ticketDto.getDescription());
        ticket.setPriority(ticketDto.getPriority());
        if (ticketDto.getUser() != null) {
            ticket.setUser(ticketDto.getUser());
        }

        ticketRepository.save(ticket);
    }

    @Override
    public Ticket getTicketById(Long id) {
        return ticketRepository.getById(id);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }
}

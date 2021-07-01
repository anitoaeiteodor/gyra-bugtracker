package com.gyra.controller;

import com.gyra.model.ticket.Ticket;
import com.gyra.model.ticket.TicketDto;
import com.gyra.model.ticket.TicketPriority;
import com.gyra.model.ticket.TicketStatus;
import com.gyra.repository.TicketRepository;
import com.gyra.repository.UserRepository;
import com.gyra.service.interfaces.TicketService;
import com.gyra.service.interfaces.UserService;
import com.gyra.util.Mappings;
import com.gyra.util.ModelAttributes;
import com.gyra.util.ViewNames;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;


@Controller
@Slf4j
@AllArgsConstructor
public class TicketController {

    private final UserService userService;
    private final TicketService ticketService;

    @GetMapping(Mappings.TICKETS)
    public String overview(Model model) {
        model.addAttribute(ModelAttributes.TICKET_LIST, ticketService.getAllTickets());
        model.addAttribute(ModelAttributes.USERNAME, userService.getCurrentUser().getFullName());
        return ViewNames.TICKETS;
    }

    @GetMapping(Mappings.ADD_TICKET)
    public String addTicket(Model model) {
        TicketDto ticketDto = new TicketDto();
        model.addAttribute(ModelAttributes.USERNAME, userService.getCurrentUser().getFullName());
        model.addAttribute(ModelAttributes.USER_LIST, userService.getAllUsers());
        model.addAttribute(ModelAttributes.NEW_TICKET, ticketDto);
        model.addAttribute(ModelAttributes.TICKET_PRIORITIES, Arrays.asList(TicketPriority.values()));
        return ViewNames.ADD_TICKET;
    }

    @GetMapping(Mappings.VIEW_TICKET)
    public String viewTicket(@RequestParam(name = "id") Long ticketId, Model model) {
        Ticket ticket = ticketService.getTicketById(ticketId);
        model.addAttribute(ModelAttributes.TICKET, ticket);
        model.addAttribute(ModelAttributes.TICKET_PRIORITIES, Arrays.asList(TicketPriority.values()));
        model.addAttribute(ModelAttributes.TICKET_STATUSES, Arrays.asList(TicketStatus.values()));
        model.addAttribute(ModelAttributes.USER_LIST, userService.getAllUsers());
        model.addAttribute(ModelAttributes.USERNAME, userService.getCurrentUser().getFullName());
        return ViewNames.VIEW_TICKET;
    }

    @PostMapping(Mappings.UPDATE_TICKET)
    public String updateTicket(@ModelAttribute(ModelAttributes.NEW_TICKET) Ticket ticket,
                               @RequestParam(name = "id") Long ticketId) {
        ticketService.updateTicket(ticketId, ticket);
        return Mappings.REDIRECT_TICKETS;
    }

    @GetMapping(Mappings.DELETE_TICKET)
    public String deleteTicket(@RequestParam(name = "id") Long ticketId) {
        log.info("Ticket id: " + ticketId);
        ticketService.deleteTicket(ticketId);
        return Mappings.REDIRECT_TICKETS;
    }

    @PostMapping(Mappings.ADD_TICKET)
    public String createTicket(@ModelAttribute(ModelAttributes.NEW_TICKET) @Validated TicketDto ticketDto) {
        ticketService.addTicket(ticketDto);
        return Mappings.REDIRECT_TICKETS;
    }
}

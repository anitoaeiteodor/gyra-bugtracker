package com.gyra.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyra.model.ticket.Ticket;
import com.gyra.model.ticket.TicketPriority;
import com.gyra.model.ticket.TicketStatus;
import com.gyra.model.user.User;
import com.gyra.repository.TicketRepository;
import com.gyra.service.interfaces.TicketService;
import com.gyra.service.interfaces.UserService;
import com.gyra.util.Mappings;
import com.gyra.util.ModelAttributes;
import com.gyra.util.ViewNames;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.rule.Mode;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/")
@AllArgsConstructor
@Slf4j
public class DashboardController {

    private final TicketService ticketService;
    private final UserService userService;

    @GetMapping
    public String getIndexPage(Model model) {

        addUserDetailsToModel(model);
        addTicketCountsToModel(model);
        addTicketPrioritiesToModel(model);

        return ViewNames.INDEX;
    }

    private void addUserDetailsToModel(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute(ModelAttributes.USERNAME, user.getFullName());
    }

    private void addTicketCountsToModel(Model model) {
        Map<TicketStatus, Long> ticketCount = ticketService.getTicketCountByStatus();

        model.addAttribute(ModelAttributes.TICKET_NOT_STARTED, ticketCount.get(TicketStatus.NOT_STARTED));
        model.addAttribute(ModelAttributes.TICKET_IN_PROGRESS, ticketCount.get(TicketStatus.IN_PROGRESS));
        model.addAttribute(ModelAttributes.TICKET_BLOCKED, ticketCount.get(TicketStatus.BLOCKED));
        model.addAttribute(ModelAttributes.TICKET_CLOSED, ticketCount.get(TicketStatus.CLOSED));
        model.addAttribute(ModelAttributes.TICKET_COUNT,
                ticketCount.values().stream().reduce(0L, Long::sum));
    }

    private void addTicketPrioritiesToModel(Model model) {
        Map<TicketPriority, Long> ticketCount = ticketService.getTicketCountByPriority();

        model.addAttribute(ModelAttributes.TICKET_LOW, ticketCount.get(TicketPriority.LOW));
        model.addAttribute(ModelAttributes.TICKET_MEDIUM, ticketCount.get(TicketPriority.MEDIUM));
        model.addAttribute(ModelAttributes.TICKET_HIGH, ticketCount.get(TicketPriority.HIGH));
        model.addAttribute(ModelAttributes.TICKET_URGENT, ticketCount.get(TicketPriority.URGENT));
    }

    @GetMapping(Mappings.LOGIN)
    public String loginPage(Model model) {
        return ViewNames.LOGIN;
    }
}

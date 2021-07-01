package com.gyra.bootstrap;

import com.gyra.model.ticket.Ticket;
import com.gyra.model.ticket.TicketPriority;
import com.gyra.model.ticket.TicketStatus;
import com.gyra.model.user.User;
import com.gyra.model.user.UserRole;
import com.gyra.repository.TicketRepository;
import com.gyra.repository.UserRepository;
import com.gyra.security.PasswordEncoder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDateTime;

@Component
@AllArgsConstructor
@Slf4j
public class BootstrapData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("Started in bootstrap");
        PasswordEncoder passwordEncoder = new PasswordEncoder();

        User noUser = new User("Unassigned", "", "bogusmail",
                passwordEncoder.bCryptPasswordEncoder().encode("random pass"), UserRole.DEVELOPER);
        userRepository.save(noUser);

        User admin = new User("admin", "root", "@",
                passwordEncoder.bCryptPasswordEncoder().encode("@"), UserRole.ADMIN);
        log.info(admin.getUsername());
        log.info("Saved admin to database");
        userRepository.save(admin);

        User adam = new User("Adam", "Fowler", "a.fowler@suse.com",
                passwordEncoder.bCryptPasswordEncoder().encode("asdf"), UserRole.DEVELOPER);
        userRepository.save(adam);

        Ticket ticket = new Ticket("Title of the ticket",
                "Description of the ticket");
        ticket.setUser(admin);
        ticket.setPriority(TicketPriority.MEDIUM);
        ticket.setStatus(TicketStatus.NOT_STARTED);
        admin.getTickets().add(ticket);
        ticketRepository.save(ticket);

        Ticket ticket1 = new Ticket("Architecture design",
                "Design the architecture");
        ticket1.setUser(admin);
        ticket1.setPriority(TicketPriority.HIGH);
        ticket1.setStatus(TicketStatus.IN_PROGRESS);
        admin.getTickets().add(ticket1);
        ticketRepository.save(ticket1);

        Ticket ticket2 = new Ticket("Drink coffee",
                "Office coffee");
        ticket2.setUser(admin);
        ticket2.setPriority(TicketPriority.LOW);
        ticket2.setStatus(TicketStatus.CLOSED);
        admin.getTickets().add(ticket2);
        ticketRepository.save(ticket2);

        Ticket ticket3 = new Ticket("Do work",
                "Code that very important hotfix");
        ticket3.setUser(adam);
        ticket3.setPriority(TicketPriority.URGENT);
        ticket3.setStatus(TicketStatus.IN_PROGRESS);
        adam.getTickets().add(ticket3);
        ticketRepository.save(ticket3);

        Ticket ticket4 = new Ticket("Backlog refinement",
                "Do refinement with team");
        ticket4.setUser(adam);
        ticket4.setPriority(TicketPriority.MEDIUM);
        ticket4.setStatus(TicketStatus.NOT_STARTED);
        adam.getTickets().add(ticket4);
        ticketRepository.save(ticket4);

        log.info("Saved tickets to repository");

    }
}


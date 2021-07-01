package com.gyra.model.ticket;

import com.gyra.model.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table
@Getter
@Setter
@ToString
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;

    private LocalDateTime creationDate;
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private TicketPriority priority;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    public Ticket() {

    }

    public Ticket(String title, String description) {
        this.creationDate = LocalDateTime.now();
        this.title = title;
        this.description = description;
        this.status = TicketStatus.NOT_STARTED;
        this.priority = TicketPriority.MEDIUM;
    }
}

package com.gyra.model.ticket;

import com.gyra.model.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@ToString
public class TicketDto {
    private User user;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private TicketPriority priority;
}

// filepath: /c:/Users/steph/Documents/portfolio/portfolio/Loto_API/src/main/java/com/fdjloto/api/controller/TicketController.java
package com.fdjloto.api.controller;

import com.fdjloto.api.model.Ticket;
import com.fdjloto.api.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/user/{userId}")
    public List<Ticket> getTicketsByUser(@PathVariable String userId) {
        return ticketService.getTicketsByUser(userId);
    }
}

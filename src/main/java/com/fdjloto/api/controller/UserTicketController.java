// filepath: /c:/Users/steph/Documents/portfolio/portfolio/Loto_API/src/main/java/com/fdjloto/api/controller/UserTicketController.java
package com.fdjloto.api.controller;

import com.fdjloto.api.model.UserTicket;
import com.fdjloto.api.service.UserTicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-tickets")
public class UserTicketController {
    private final UserTicketService userTicketService;

    public UserTicketController(UserTicketService userTicketService) {
        this.userTicketService = userTicketService;
    }

    @GetMapping("/user/{userId}")
    public List<UserTicket> getTicketsByUser(@PathVariable String userId) {
        return userTicketService.getTicketsByUser(userId);
    }

    @GetMapping("/ticket/{ticketId}")
    public List<UserTicket> getUsersByTicket(@PathVariable String ticketId) {
        return userTicketService.getUsersByTicket(ticketId);
    }

    @PostMapping("/assign")
    public UserTicket assignUserToTicket(@RequestBody UserTicket userTicket) {
        return userTicketService.assignUserToTicket(userTicket);
    }
}

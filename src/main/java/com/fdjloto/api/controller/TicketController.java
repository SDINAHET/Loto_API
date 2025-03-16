package com.fdjloto.api.controller;

import com.fdjloto.api.dto.TicketDTO;
import com.fdjloto.api.model.Ticket;
import com.fdjloto.api.model.User;
import com.fdjloto.api.security.JwtUtils;
import com.fdjloto.api.service.TicketService;
import com.fdjloto.api.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import com.fdjloto.api.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * **Controller for managing lottery tickets.**
 */
@RestController
@RequestMapping("/api/tickets")
@Tag(name = "Ticket Management", description = "Endpoints for managing user tickets")
@SecurityRequirement(name = "bearerAuth")
@SecurityRequirement(name = "jwtCookieAuth")
@CrossOrigin(
    origins = "http://127.0.0.1:5500",
    allowCredentials = "true",
    allowedHeaders = "*",
    methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
)
public class TicketController {

    private final TicketService ticketService;
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private static final String USER_NOT_FOUND = "User not found";

    public TicketController(TicketService ticketService, UserService userService, JwtUtils jwtUtils) {
        this.ticketService = ticketService;
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @Autowired
    private TicketRepository ticketRepository;

    /**
     * 🔥 Retrieves all tickets for a user (via JWT Cookie).
     * - Regular User: can only access their own tickets.
     * - Admin: can access all tickets.
     *
     * @param request HTTP request to extract JWT from cookies.
     * @return **200 OK** - List of user tickets.
     * @return **403 FORBIDDEN** - If user is not authenticated.
     */
    @Operation(summary = "Retrieve all user tickets", description = "Admins can see all tickets, users only their own.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successfully retrieved user tickets."),
        @ApiResponse(responseCode = "403", description = "User not authenticated.")
    })
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<TicketDTO>> getUserTickets(HttpServletRequest request) {
        Optional<String> jwtOpt = getJwtFromCookie(request);
        if (jwtOpt.isEmpty()) {
            return ResponseEntity.status(403).build();
        }
        String jwt = jwtOpt.get();
        String email = jwtUtils.getUserFromJwtToken(jwt);
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException(USER_NOT_FOUND));

        // 🔥 Admin gets all tickets, User gets only their own
        List<TicketDTO> tickets = user.isAdmin()
            ? ticketService.getAllTickets()
            : ticketService.getTicketsByUserId(user.getId());

        return ResponseEntity.ok(tickets);
    }

    /**
     * 🔥 Retrieves a specific ticket (via JWT Cookie).
     * - Regular User: can only see their own tickets.
     * - Admin: can see all tickets.
     *
     * @param ticketId ID of the ticket.
     * @param request HTTP request to extract JWT from cookies.
     * @return **200 OK** - Ticket details.
     * @return **403 FORBIDDEN** - If access is denied.
     * @return **404 NOT FOUND** - If ticket does not exist.
     */
    @Operation(summary = "Retrieve a ticket by ID", description = "Admins can see all tickets, users only their own.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successfully retrieved ticket."),
        @ApiResponse(responseCode = "403", description = "User not authorized."),
        @ApiResponse(responseCode = "404", description = "Ticket not found.")
    })
    @GetMapping("/{ticketId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable String ticketId, HttpServletRequest request) {
        Optional<String> jwtOpt = getJwtFromCookie(request);
        if (jwtOpt.isEmpty()) {
            return ResponseEntity.status(403).build();
        }
        String jwt = jwtOpt.get();
        String email = jwtUtils.getUserFromJwtToken(jwt);
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException(USER_NOT_FOUND));

        Ticket ticket = ticketService.getTicketById(ticketId);

        if (user.isAdmin() || ticket.getUser().getId().equals(user.getId())) {
            return ResponseEntity.ok(new TicketDTO(ticket));
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    /**
     * 🔥 Deletes a ticket (User can delete only their own tickets, Admin can delete any).
     *
     * @param ticketId ID of the ticket.
     * @param request HTTP request to extract JWT from cookies.
     * @return **204 NO CONTENT** - If ticket deleted successfully.
     * @return **403 FORBIDDEN** - If user is not authorized.
     */
    @Operation(summary = "Delete a ticket", description = "Users can delete their own tickets, admins can delete any ticket.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Ticket successfully deleted."),
        @ApiResponse(responseCode = "403", description = "User not authorized.")
    })
    @DeleteMapping("/{ticketId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Void> deleteTicket(@PathVariable String ticketId, HttpServletRequest request) {
        Optional<String> jwtOpt = getJwtFromCookie(request);
        if (jwtOpt.isEmpty()) {
            return ResponseEntity.status(403).build();
        }
        String jwt = jwtOpt.get();
        String email = jwtUtils.getUserFromJwtToken(jwt);
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException(USER_NOT_FOUND));

        Ticket ticket = ticketService.getTicketById(ticketId);

        if (user.isAdmin() || ticket.getUser().getId().equals(user.getId())) {
            ticketService.deleteTicket(ticketId, user.getId());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    /**
     * 🔑 Retrieves the JWT from the "jwtToken" cookie.
     * Used to authenticate and get user information.
     */
    private Optional<String> getJwtFromCookie(HttpServletRequest request) {
        if (request.getCookies() != null) {
            return Arrays.stream(request.getCookies())
                    .filter(cookie -> "jwtToken".equals(cookie.getName()))
                    .map(cookie -> cookie.getValue())
                    .findFirst();
        }
        return Optional.empty();
    }

    /**
     * 🔥 Creates a new ticket for the authenticated user.
     * - Users can create their own tickets.
     * - Admins can create tickets for any user.
     *
     * @param ticketDTO The ticket details to be created.
     * @param request HTTP request to extract JWT from cookies.
     * @return **201 CREATED** - Ticket successfully created.
     * @return **403 FORBIDDEN** - If the user is not authenticated.
     */
    @Operation(summary = "Create a new ticket", description = "Users can create their own tickets. Admins can create for any user.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Ticket created successfully."),
        @ApiResponse(responseCode = "403", description = "User not authenticated."),
        @ApiResponse(responseCode = "400", description = "Invalid ticket data.")
    })
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketDTO ticketDTO, HttpServletRequest request) {
        Optional<String> jwtOpt = getJwtFromCookie(request);
        if (jwtOpt.isEmpty()) {
            return ResponseEntity.status(403).body(null);
        }
        String jwt = jwtOpt.get();
        String email = jwtUtils.getUserFromJwtToken(jwt);

        // 🔍 Look up the user by email
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 🔥 Create the ticket for the authenticated user
        Ticket newTicket = ticketService.createTicket(user.getId(), ticketDTO);

        return ResponseEntity.ok(new TicketDTO(newTicket));
    }

    /**
     * 🔥 Updates an existing ticket.
     * - Users can update only their own tickets.
     * - Admins can update any ticket.
     *
     * @param ticketId ID of the ticket to update.
     * @param ticketDTO The updated ticket details.
     * @param request HTTP request to extract JWT from cookies.
     * @return **200 OK** - Ticket updated successfully.
     * @return **403 FORBIDDEN** - If user is not authorized to update this ticket.
     * @return **404 NOT FOUND** - If the ticket does not exist.
     * @return **400 BAD REQUEST** - If the ticket data is invalid.
     */
    @Operation(summary = "Update an existing ticket", description = "Users can update their own tickets. Admins can update any ticket.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Ticket updated successfully."),
        @ApiResponse(responseCode = "403", description = "User not authorized."),
        @ApiResponse(responseCode = "404", description = "Ticket not found."),
        @ApiResponse(responseCode = "400", description = "Invalid ticket data.")
    })
    @PutMapping("/{ticketId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<TicketDTO> updateTicket(@PathVariable String ticketId, @RequestBody TicketDTO ticketDTO, HttpServletRequest request) {
        Optional<String> jwtOpt = getJwtFromCookie(request);
        if (jwtOpt.isEmpty()) {
            return ResponseEntity.status(403).body(null);
        }

        String jwt = jwtOpt.get();
        String email = jwtUtils.getUserFromJwtToken(jwt);

        // 🔍 Log for debugging
        System.out.println("✅ Extracted JWT: " + jwt);
        System.out.println("✅ Retrieved Email: " + email);

        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 🔍 Verify and retrieve the ticket
        Ticket existingTicket = ticketService.getTicketById(ticketId);
        if (existingTicket == null) {
            System.out.println("❌ Ticket not found with ID: " + ticketId);
            return ResponseEntity.status(404).build();
        }

        System.out.println("✅ Ticket found: " + existingTicket.getId());

        // 🔐 Check permissions
        if (!user.isAdmin() && !existingTicket.getUser().getId().equals(user.getId())) {
            System.out.println("❌ Access denied: User is not the owner of this ticket.");
            return ResponseEntity.status(403).build();
        }

        // 🔥 Update fields
        existingTicket.setNumbers(ticketDTO.getNumbers());

        try {
            existingTicket.setChanceNumber(Integer.parseInt(ticketDTO.getChanceNumber()));
        } catch (NumberFormatException e) {
            System.out.println("❌ Error parsing chanceNumber: " + ticketDTO.getChanceNumber());
            return ResponseEntity.status(400).build();
        }

        if (ticketDTO.getDrawDate() != null && !ticketDTO.getDrawDate().isEmpty()) {
            existingTicket.setDrawDate(LocalDate.parse(ticketDTO.getDrawDate()));
        }

        // ✅ Handle updatedAt field
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (ticketDTO.getUpdatedAt() != null && !ticketDTO.getUpdatedAt().isEmpty()) {
            try {
                existingTicket.setUpdatedAt(LocalDateTime.parse(ticketDTO.getUpdatedAt(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            } catch (Exception e) {
                System.out.println("❌ Invalid format for updatedAt: " + ticketDTO.getUpdatedAt());
                return ResponseEntity.status(400).build();
            }
        } else {
            existingTicket.setUpdatedAt(LocalDateTime.now());
        }

        // ✅ Save and return updated object
        Ticket updatedTicket = ticketRepository.save(existingTicket);
        System.out.println("✅ Ticket updated: " + updatedTicket.getId());

        return ResponseEntity.ok(new TicketDTO(updatedTicket));
    }
}

package com.fdjloto.api.controller;

import com.fdjloto.api.dto.TicketDTO;
import com.fdjloto.api.model.Ticket;
import com.fdjloto.api.model.User;
import com.fdjloto.api.security.JwtUtils;
import com.fdjloto.api.service.TicketService;
import com.fdjloto.api.service.UserService;

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


@RestController
@RequestMapping("/api/tickets")
@Tag(name = "Ticket Management", description = "Endpoints for managing Tickets")
@SecurityRequirement(name = "bearerAuth")
@SecurityRequirement(name = "jwtCookieAuth")
@CrossOrigin(
    origins = "http://127.0.0.1:5500",
    allowCredentials = "true",
    allowedHeaders = "*",
    methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
)
// @CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true")
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
     * 🔥 Récupère tous les tickets du joueur (via JWT Cookie).
     * - User : récupère **ses propres tickets**.
     * - Admin : récupère **tous les tickets**.
     */
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

        // 🔥 Admin → tous les tickets | User → seulement ses tickets
        List<TicketDTO> tickets = user.isAdmin()
            ? ticketService.getAllTickets()
            : ticketService.getTicketsByUserId(user.getId());


        return ResponseEntity.ok(tickets);
    }

    /**
     * 🔥 Récupère un ticket spécifique (via JWT Cookie).
     * - User : ne peut voir que **ses propres tickets**.
     * - Admin : peut voir **tous les tickets**.
     */
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
     * 🔥 Suppression d'un ticket (User = uniquement ses propres tickets, Admin = tous).
     */
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
     * 🔑 Récupère le JWT depuis le cookie "jwtToken".
     * Ajoute cette méthode pour récupérer l'utilisateur à partir du JWT dans le cookie
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

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketDTO ticketDTO, HttpServletRequest request) {
        Optional<String> jwtOpt = getJwtFromCookie(request);
        if (jwtOpt.isEmpty()) {
            return ResponseEntity.status(403).body(null);
        }
        String jwt = jwtOpt.get();
        String email = jwtUtils.getUserFromJwtToken(jwt);

        // 🔍 Recherche de l'utilisateur par email
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 🔥 Création du ticket pour l'utilisateur connecté
        Ticket newTicket = ticketService.createTicket(user.getId(), ticketDTO);

        return ResponseEntity.ok(new TicketDTO(newTicket));
    }

    @PutMapping("/{ticketId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<TicketDTO> updateTicket(@PathVariable String ticketId, @RequestBody TicketDTO ticketDTO, HttpServletRequest request) {
        Optional<String> jwtOpt = getJwtFromCookie(request);
        if (jwtOpt.isEmpty()) {
            return ResponseEntity.status(403).body(null);
        }

        String jwt = jwtOpt.get();
        String email = jwtUtils.getUserFromJwtToken(jwt);

        // 🔍 Log pour vérifier l'utilisateur
        System.out.println("✅ JWT extrait : " + jwt);
        System.out.println("✅ Email récupéré : " + email);

        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 🔍 Vérification et récupération du ticket
        Ticket existingTicket = ticketService.getTicketById(ticketId);
        if (existingTicket == null) {
            System.out.println("❌ Ticket introuvable avec ID : " + ticketId);
            return ResponseEntity.status(404).build();
        }

        System.out.println("✅ Ticket trouvé : " + existingTicket.getId());

        // 🔐 Vérification des permissions
        if (!user.isAdmin() && !existingTicket.getUser().getId().equals(user.getId())) {
            System.out.println("❌ Accès refusé : l'utilisateur n'est pas propriétaire de ce ticket.");
            return ResponseEntity.status(403).build();
        }

        // 🔥 Mise à jour des champs
        existingTicket.setNumbers(ticketDTO.getNumbers());

        try {
            existingTicket.setChanceNumber(Integer.parseInt(ticketDTO.getChanceNumber()));
        } catch (NumberFormatException e) {
            System.out.println("❌ Erreur de conversion du chanceNumber : " + ticketDTO.getChanceNumber());
            return ResponseEntity.status(400).build();
        }

        if (ticketDTO.getDrawDate() != null && !ticketDTO.getDrawDate().isEmpty()) {
            existingTicket.setDrawDate(LocalDate.parse(ticketDTO.getDrawDate()));
        }

        // ✅ Vérification et conversion de updatedAt
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (ticketDTO.getUpdatedAt() != null && !ticketDTO.getUpdatedAt().isEmpty()) {
            try {
                existingTicket.setUpdatedAt(LocalDateTime.parse(ticketDTO.getUpdatedAt(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            } catch (Exception e) {
                System.out.println("❌ Erreur de format du champ updatedAt : " + ticketDTO.getUpdatedAt());
                return ResponseEntity.status(400).build();
            }
        } else {
            existingTicket.setUpdatedAt(LocalDateTime.now());
        }


        // ✅ Sauvegarde et retour de l'objet mis à jour
        Ticket updatedTicket = ticketRepository.save(existingTicket);
        System.out.println("✅ Ticket mis à jour : " + updatedTicket.getId());

        return ResponseEntity.ok(new TicketDTO(updatedTicket));
    }


}

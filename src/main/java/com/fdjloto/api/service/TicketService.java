package com.fdjloto.api.service;

import com.fdjloto.api.dto.TicketDTO;
import com.fdjloto.api.model.Ticket;
import com.fdjloto.api.model.User;
import com.fdjloto.api.repository.TicketRepository;
import com.fdjloto.api.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import com.fdjloto.api.exception.TicketNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.Arrays;
import jakarta.servlet.http.Cookie;


@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TicketService(TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    public Ticket createTicket(String userId, TicketDTO ticketDTO) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setNumbers(ticketDTO.getNumbers());
        ticket.setChanceNumber(Integer.parseInt(ticketDTO.getChanceNumber()));

        // ✅ Vérification et conversion de `drawDate`
        if (ticketDTO.getDrawDate() != null && !ticketDTO.getDrawDate().isEmpty()) {
            LocalDate drawDate = LocalDate.parse(ticketDTO.getDrawDate());
            ticket.setDrawDate(drawDate);
            ticket.setDrawDay(getDrawDay(drawDate)); // 🔥 Définit automatiquement `draw_day`
        }

        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setUpdatedAt(LocalDateTime.now());

        return ticketRepository.save(ticket);
    }

    // public Ticket updateTicket(String ticketId, TicketDTO ticketDTO) {
    //     Ticket existingTicket = getTicketById(ticketId);
    //     existingTicket.setNumbers(ticketDTO.getNumbers());
    //     existingTicket.setChanceNumber(Integer.parseInt(ticketDTO.getChanceNumber()));

    //     if (ticketDTO.getDrawDate() != null && !ticketDTO.getDrawDate().isEmpty()) {
    //         existingTicket.setDrawDate(LocalDate.parse(ticketDTO.getDrawDate()));
    //     }

    //     // ✅ Vérification et conversion des timestamps
    //     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    //     if (ticketDTO.getUpdatedAt() != null && !ticketDTO.getUpdatedAt().isEmpty()) {
    //         existingTicket.setUpdatedAt(LocalDateTime.parse(ticketDTO.getUpdatedAt(), formatter));
    //     } else {
    //         existingTicket.setUpdatedAt(LocalDateTime.now());
    //     }

    //     return ticketRepository.save(existingTicket);
    // }

    public Ticket updateTicket(String ticketId, TicketDTO ticketDTO) {
        Ticket existingTicket = getTicketById(ticketId);
        existingTicket.setNumbers(ticketDTO.getNumbers());

        // ✅ Conversion sûre du chanceNumber
        try {
            existingTicket.setChanceNumber(Integer.parseInt(ticketDTO.getChanceNumber()));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Le numéro chance doit être un entier valide.");
        }

        // ✅ Vérification et mise à jour de drawDate + drawDay
        if (ticketDTO.getDrawDate() != null && !ticketDTO.getDrawDate().isEmpty()) {
            LocalDate newDrawDate = LocalDate.parse(ticketDTO.getDrawDate());

            // 🔥 Vérification avant mise à jour
            System.out.println("🔍 Ancien DrawDate: " + existingTicket.getDrawDate());
            System.out.println("🔍 Nouveau DrawDate: " + newDrawDate);

            if (!newDrawDate.equals(existingTicket.getDrawDate())) {
                existingTicket.setDrawDate(newDrawDate);

                // ✅ Mise à jour automatique de drawDay
                String newDrawDay = getDrawDay(newDrawDate);
                System.out.println("🎯 Nouveau DrawDay calculé: " + newDrawDay);
                existingTicket.setDrawDay(newDrawDay);
            }
        }

        // ✅ Mise à jour du timestamp updatedAt
        existingTicket.setUpdatedAt(LocalDateTime.now());

        // ✅ Sauvegarde du ticket mis à jour
        return ticketRepository.save(existingTicket);
    }



    // ✅ Méthode pour récupérer le jour correspondant à la date
    // private String getDrawDay(LocalDate drawDate) {
    //     return drawDate.getDayOfWeek()
    //             .getDisplayName(java.time.format.TextStyle.FULL, Locale.FRENCH)
    //             .toLowerCase(); // 🔥 Convertit en français (lundi, mardi...)
    // }



    private String getDrawDay(LocalDate drawDate) {
        return drawDate.getDayOfWeek()
                .getDisplayName(java.time.format.TextStyle.FULL, Locale.FRENCH)
                .toLowerCase(); // 🔥 Convertit en français (lundi, mardi...)
    }

    public List<Ticket> getTicketsByEmail(String email) {
        return ticketRepository.findByUserEmail(email);
    }


    public List<TicketDTO> getAllTickets() {
        return ticketRepository.findAll().stream().map(ticket -> new TicketDTO(ticket)).toList();
    }

    public List<TicketDTO> getTicketsByUserId(String userId) {
        return ticketRepository.findByUserId(userId).stream().map(ticket -> new TicketDTO(ticket)).toList();
    }

    public Ticket getTicketById(String ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found with ID: " + ticketId));
    }

    public Ticket updateTicket(String ticketId, Ticket updatedTicket, String userId) {
        Ticket existingTicket = getTicketById(ticketId);
        if (!existingTicket.getUser().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized: You don't own this ticket");
        }
        existingTicket.setNumbers(updatedTicket.getNumbers());
        existingTicket.setChanceNumber(updatedTicket.getChanceNumber());
        existingTicket.setDrawDate(updatedTicket.getDrawDate());
        return ticketRepository.save(existingTicket);
    }

    public void deleteTicket(String ticketId, String userId) {
        Ticket ticket = getTicketById(ticketId);
        if (!ticket.getUser().getId().equals(userId) && !isAdmin(userId)) {
            throw new RuntimeException("Unauthorized: You don't own this ticket");
        }
        ticketRepository.deleteById(ticketId);
    }

    private boolean isAdmin(String userId) {
        return userRepository.findById(userId)
            .map(User::isAdmin)
            .orElse(false);
    }


    public String getDrawDay(String drawDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(drawDate, formatter);
        return date.getDayOfWeek().getDisplayName(java.time.format.TextStyle.FULL, Locale.FRENCH).toUpperCase();
    }

    private String convertTimestampToDateTime(long timestamp) {
        LocalDateTime dateTime = Instant.ofEpochMilli(timestamp)
                                        .atZone(ZoneId.systemDefault())
                                        .toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }

    private Optional<String> getJwtFromCookie(HttpServletRequest request) {
        if (request.getCookies() == null) {
            System.out.println("⚠️ Aucun cookie trouvé !");
            return Optional.empty();
        }

        Optional<String> jwtOpt = Arrays.stream(request.getCookies())
            .filter(cookie -> "jwtToken".equals(cookie.getName()))
            .map(Cookie::getValue)
            .findFirst();

        jwtOpt.ifPresentOrElse(
            jwt -> System.out.println("✅ JWT trouvé : " + jwt),
            () -> System.out.println("⚠️ Aucun JWT trouvé dans les cookies.")
        );

        return jwtOpt;
    }







}

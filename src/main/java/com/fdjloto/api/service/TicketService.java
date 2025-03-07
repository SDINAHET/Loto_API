// // package com.fdjloto.api.service;

// // import com.fdjloto.api.model.Ticket;
// // import com.fdjloto.api.repository.TicketRepository;
// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.stereotype.Service;

// // import java.util.List;
// // import java.util.Optional;
// // import java.util.UUID;

// // @Service
// // public class TicketService {

// //     @Autowired
// //     private TicketRepository ticketRepository;

// //     /**
// //      * Crée un nouveau ticket pour un utilisateur.
// //      * @param ticket Objet ticket contenant les informations.
// //      * @return Le ticket cr .
// //      */
// //     public Ticket createTicket(Ticket ticket) {
// //         return ticketRepository.save(ticket);
// //     }

// //     /**
// //      * Récupére tous les tickets d'un utilisateur.
// //      * @param userId ID de l'utilisateur.
// //      * @return La liste des tickets appartenant  l'utilisateur.
// //      */
// // 	public List<Ticket> getUserTickets(UUID userId) {
// // 		return ticketRepository.findByUserId(userId);
// // 	}



// //     /**
// //      * Récupère un ticket spécifique par son ID.
// //      * @param ticketId ID du ticket.
// //      * @return Le ticket correspondant.
// //      * @throws RuntimeException si le ticket n'est pas trouvé.
// //      */
// //     public Ticket getTicketById(UUID ticketId) {
// //         Optional<Ticket> ticket = ticketRepository.findById(ticketId);
// //         return ticket.orElseThrow(() -> new RuntimeException("Ticket not found"));
// //     }

// //     /**
// //      * Met à jour les informations d'un ticket existant.
// //      * @param ticketId ID du ticket  mettre  jour.
// //      * @param updatedTicket Objet ticket contenant les nouvelles valeurs.
// //      * @return Le ticket mis  jour.
// //      */
// //     public Ticket updateTicket(UUID ticketId, Ticket updatedTicket) {
// //         Ticket existingTicket = getTicketById(ticketId);
// //         existingTicket.setNumbers(updatedTicket.getNumbers());
// //         existingTicket.setChanceNumber(updatedTicket.getChanceNumber());
// //         return ticketRepository.save(existingTicket);
// //     }

// //     public List<Ticket> getAllTickets() {
// //         return ticketRepository.findAll(); // Assure-toi que `ticketRepository` est bien défini.
// //     }


// //     /**
// //      * Supprime un ticket spécifique par son ID.
// //      * @param ticketId ID du ticket à supprimer.
// //      */

// //     public void deleteTicket(UUID ticketId) {
// //         ticketRepository.deleteById(ticketId);
// //     }
// // }

// package com.fdjloto.api.service;

// import com.fdjloto.api.model.Ticket;
// import com.fdjloto.api.repository.TicketRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.UUID;

// @Service
// public class TicketService {

//     @Autowired
//     private TicketRepository ticketRepository;

//     /**
//      * 🔥 Récupère tous les tickets d'un utilisateur par email.
//      * @param email Email de l'utilisateur.
//      * @return Liste des tickets appartenant à l'utilisateur.
//      */
//     // public List<Ticket> getTicketsByEmail(String email) {
//     //     return ticketRepository.findByEmail(email);
//     // }
//     public List<Ticket> getTicketsByEmail(String email) {
//         return ticketRepository.findByUserEmail(email);
//     }

//     /**
//      * 🔥 Récupère tous les tickets.
//      * Utilisé uniquement par un ADMIN.
//      * @return Liste de tous les tickets.
//      */
//     public List<Ticket> getAllTickets() {
//         return ticketRepository.findAll();
//     }

//     /**
//      * 🔥 Récupère un ticket spécifique par son ID.
//      * @param ticketId ID du ticket.
//      * @return Le ticket correspondant.
//      */
//     public Ticket getTicketById(UUID ticketId) {
//         return ticketRepository.findById(ticketId)
//                 .orElseThrow(() -> new RuntimeException("Ticket not found"));
//     }
// }


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

    // public Ticket createTicket(String userId) {
    //     User user = userRepository.findById(userId)
    //             .orElseThrow(() -> new RuntimeException("User not found"));
    //     Ticket ticket = new Ticket();
    //     ticket.setUser(user);
    //     return ticketRepository.save(ticket);
    // }

    /**
     * 🔥 Crée un nouveau ticket pour un utilisateur donné.
     */
    // public Ticket createTicket(String userId, TicketDTO ticketDTO) {
    //     User user = userRepository.findById(userId)
    //             .orElseThrow(() -> new RuntimeException("User not found"));

    //     Ticket ticket = new Ticket();
    //     ticket.setUser(user);
    //     ticket.setNumbers(ticketDTO.getNumbers());

    //     // ✅ Conversion du numéro chance en entier
    //     ticket.setChanceNumber(Integer.parseInt(ticketDTO.getChanceNumber()));
    //     ticket.setDrawDate(ticketDTO.getDrawDate());

    //     return ticketRepository.save(ticket);
    // }

    // /**
    //  * 🔥 Met à jour un ticket existant.
    //  */
    // public Ticket updateTicket(String ticketId, TicketDTO ticketDTO) {
    //     Ticket existingTicket = getTicketById(ticketId);
    //     existingTicket.setNumbers(ticketDTO.getNumbers());

    //     // ✅ Conversion sécurisée du numéro chance
    //     existingTicket.setChanceNumber(Integer.parseInt(ticketDTO.getChanceNumber()));
    //     existingTicket.setDrawDate(ticketDTO.getDrawDate());

    //     return ticketRepository.save(existingTicket);
    // }

//     public Ticket createTicket(String userId, TicketDTO ticketDTO) {
//     User user = userRepository.findById(userId)
//             .orElseThrow(() -> new RuntimeException("User not found"));

//     Ticket ticket = new Ticket();
//     ticket.setUser(user);
//     ticket.setNumbers(ticketDTO.getNumbers());
//     ticket.setChanceNumber(Integer.parseInt(ticketDTO.getChanceNumber()));

//     // ✅ Conversion de chanceNumber en int
//     ticket.setChanceNumber(Integer.parseInt(ticketDTO.getChanceNumber()));

//     // ✅ Vérification et conversion de drawDate
//     if (ticketDTO.getDrawDate() != null && !ticketDTO.getDrawDate().isEmpty()) {
//         ticket.setDrawDate(LocalDate.parse(ticketDTO.getDrawDate()));
//     }

//     return ticketRepository.save(ticket);
// }

    // public Ticket createTicket(String userId, TicketDTO ticketDTO) {
    //     User user = userRepository.findById(userId)
    //             .orElseThrow(() -> new RuntimeException("User not found"));

    //     Ticket ticket = new Ticket();
    //     ticket.setUser(user);
    //     ticket.setNumbers(ticketDTO.getNumbers());
    //     ticket.setChanceNumber(Integer.parseInt(ticketDTO.getChanceNumber()));

    //     // ✅ Vérification et conversion de `drawDate`
    //     if (ticketDTO.getDrawDate() != null && !ticketDTO.getDrawDate().isEmpty()) {
    //         LocalDate drawDate = LocalDate.parse(ticketDTO.getDrawDate());
    //         ticket.setDrawDate(drawDate);
    //         ticket.setDrawDay(getDrawDay(drawDate)); // 🔥 Définit automatiquement `draw_day`
    //     }

    //     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    //     if (ticketDTO.getCreatedAt() != null && !ticketDTO.getCreatedAt().isEmpty()) {
    //         ticket.setCreatedAt(LocalDateTime.parse(ticketDTO.getCreatedAt(), formatter)); // ✅ Conversion correcte
    //     } else {
    //         ticket.setCreatedAt(LocalDateTime.now());
    //     }

    //     if (ticketDTO.getUpdatedAt() != null && !ticketDTO.getUpdatedAt().isEmpty()) {
    //         ticket.setUpdatedAt(LocalDateTime.parse(ticketDTO.getUpdatedAt(), formatter)); // ✅ Conversion correcte
    //     } else {
    //         ticket.setUpdatedAt(LocalDateTime.now());
    //     }
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






    //     return ticketRepository.save(ticket); // ✅ Sauvegarde du ticket avec `draw_day` défini
    // }








    // public Ticket updateTicket(String ticketId, TicketDTO ticketDTO) {
    //     Ticket existingTicket = getTicketById(ticketId);
    //     existingTicket.setNumbers(ticketDTO.getNumbers());

    //     // ✅ Conversion sécurisée du numéro chance
    //     existingTicket.setChanceNumber(Integer.parseInt(ticketDTO.getChanceNumber()));

    //     // ✅ Vérification et conversion de drawDate
    //     if (ticketDTO.getDrawDate() != null && !ticketDTO.getDrawDate().isEmpty()) {
    //         existingTicket.setDrawDate(LocalDate.parse(ticketDTO.getDrawDate()));
    //     }

    //     // ✅ Vérification et conversion des timestamps
    //     if (ticketDTO.getUpdatedAt() != null && !ticketDTO.getUpdatedAt().isEmpty()) {
    //         existingTicket.setUpdatedAt(ticketDTO.getUpdatedAt()); // ✅ Stocke en `String`
    //     } else {
    //         existingTicket.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    //     }


    //     return ticketRepository.save(existingTicket);
    // }

    public Ticket updateTicket(String ticketId, TicketDTO ticketDTO) {
        Ticket existingTicket = getTicketById(ticketId);
        existingTicket.setNumbers(ticketDTO.getNumbers());
        existingTicket.setChanceNumber(Integer.parseInt(ticketDTO.getChanceNumber()));

        if (ticketDTO.getDrawDate() != null && !ticketDTO.getDrawDate().isEmpty()) {
            existingTicket.setDrawDate(LocalDate.parse(ticketDTO.getDrawDate()));
        }

        // ✅ Vérification et conversion des timestamps
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        if (ticketDTO.getUpdatedAt() != null && !ticketDTO.getUpdatedAt().isEmpty()) {
            existingTicket.setUpdatedAt(LocalDateTime.parse(ticketDTO.getUpdatedAt(), formatter));
        } else {
            existingTicket.setUpdatedAt(LocalDateTime.now());
        }

        return ticketRepository.save(existingTicket);
    }


    private String getDrawDay(LocalDate drawDate) {
        return drawDate.getDayOfWeek()
                .getDisplayName(java.time.format.TextStyle.FULL, Locale.FRENCH)
                .toLowerCase(); // 🔥 Convertit en français (lundi, mardi...)
    }
    // if (ticket.getDrawDate() != null) {
    //     ticket.setDrawDay(getDrawDay(ticket.getDrawDate())); // ✅ Définit automatiquement `draw_day`
    // }




    // public List<Ticket> getTicketsByUserId(String userId) {
    //     return ticketRepository.findByUserId(userId);
    // }

    public List<Ticket> getTicketsByEmail(String email) {
        return ticketRepository.findByUserEmail(email);
    }

    // public List<Ticket> getAllTickets() {
    //     return ticketRepository.findAll();
    // }

    // public Ticket getTicketById(String ticketId) {
    //     return ticketRepository.findById(ticketId)
    //             .orElseThrow(() -> new RuntimeException("Ticket not found"));
    // }

    // public Ticket getTicketById(String ticketId) {
    //     Ticket ticket = ticketRepository.findById(ticketId)
    //             .orElseThrow(() -> new RuntimeException("Ticket not found"));

    //     // 🔥 Chargement explicite pour éviter l'erreur LazyInitializationException
    //     ticket.getUser().getId(); // Force Hibernate à charger l'utilisateur
    //     return ticket;
    // }
    // public List<TicketDTO> getTicketsByUserId(String userId) {
    //     List<Ticket> tickets = ticketRepository.findByUserId(userId);
    //     return tickets.stream().map(TicketDTO::new).toList();
    // }

//     public Ticket getTicketById(String ticketId) {
//     return ticketRepository.findById(ticketId)
//             .orElseThrow(() -> new RuntimeException("Ticket not found"));
// }

    // public Ticket getTicketById(String ticketId) {
    // Ticket ticket = ticketRepository.findById(ticketId)
    // .orElseThrow(() -> new TicketNotFoundException("Ticket with ID " + ticketId + " not found"));

    // // 🔥 Forcer Hibernate à charger l'utilisateur avant que la session ne se ferme
    // ticket.getUser().getEmail(); // Hibernate charge `User`

    // return ticket;
    // }
//     public List<TicketDTO> getAllTickets() {
//     return ticketRepository.findAll()
//             .stream()
//             .map(TicketDTO::new) // 🔥 Conversion en DTO pour éviter LazyInitializationException
//             .collect(Collectors.toList());
// }

    // public List<TicketDTO> getAllTickets() {
    //     return ticketRepository.findAll()
    //             .stream()
    //             .map(TicketDTO::new) // 🔥 Convertit chaque Ticket en TicketDTO
    //             .collect(Collectors.toList());
    // }
    public List<TicketDTO> getAllTickets() {
        return ticketRepository.findAll().stream().map(ticket -> new TicketDTO(ticket)).toList();
    }

    public List<TicketDTO> getTicketsByUserId(String userId) {
        return ticketRepository.findByUserId(userId).stream().map(ticket -> new TicketDTO(ticket)).toList();
    }



    // public Ticket getTicketById(String ticketId) {
    //     return ticketRepository.findById(ticketId)
    //             .orElseThrow(() -> new RuntimeException("Ticket not found"));
    // }

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

    // public void deleteTicket(String ticketId, String userId) {
    //     Ticket ticket = getTicketById(ticketId);
    //     if (!ticket.getUser().getId().equals(userId)) {
    //         throw new RuntimeException("Unauthorized: You don't own this ticket");
    //     }
    //     ticketRepository.deleteById(ticketId);
    // }

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

    // private Optional<String> getJwtFromCookie(HttpServletRequest request) {
    //     return Arrays.stream(request.getCookies())
    //         .filter(cookie -> "jwtToken".equals(cookie.getName()))
    //         .map(Cookie::getValue)
    //         .findFirst();
    // }
    // private Optional<String> getJwtFromCookie(HttpServletRequest request) {
    //     if (request.getCookies() == null) {
    //         return Optional.empty();
    //     }
    //     return Arrays.stream(request.getCookies())
    //         .filter(cookie -> "jwtToken".equals(cookie.getName()))
    //         .map(Cookie::getValue)
    //         .findFirst();
    // }
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

// package com.fdjloto.api.controller;

// import com.fdjloto.api.model.Ticket;
// import com.fdjloto.api.service.TicketService;

// import io.swagger.v3.oas.annotations.security.SecurityRequirement;
// import io.swagger.v3.oas.annotations.tags.Tag;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.Map;
// import java.util.List;
// import java.util.UUID;
// import java.util.stream.Collectors;

// @RestController
// @RequestMapping("/api/tickets")
// @Tag(name = "Ticket Management", description = "Endpoints for managing Tickets for user accounts")
// @SecurityRequirement(name = "BearerAuth") // 🔐 Ajout de l'authentification JWT
// @SecurityRequirement(name = "jwtCookieAuth") // 🔐 Ajout de l'authentification JWT via cookie

// @CrossOrigin(origins = "http://127.0.0.1:5500") // 🔥 Autorise CORS pour Live Server
// public class TicketController {

//     @Autowired
//     private TicketService ticketService;

//     /**
//      * Crée un nouveau ticket pour un utilisateur.
//      * @param ticket Objet ticket contenant les informations.
//      * @return Le ticket créé.
//      */
//     @PostMapping
//     public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
//         return ResponseEntity.ok(ticketService.createTicket(ticket));
//     }

//     /**
//      * Récupère tous les tickets d'un utilisateur donné.
//      * @param userId ID de l'utilisateur.
//      * @return Liste des tickets appartenant à l'utilisateur.
//      */
// 	@GetMapping
// 	public ResponseEntity<List<Ticket>> getUserTickets(@RequestParam UUID userId) {
// 		return ResponseEntity.ok(ticketService.getUserTickets(userId));
// 	}


//     /**
//      * Récupère un ticket spécifique par son ID.
//      * @param ticketId ID du ticket.
//      * @return Le ticket correspondant.
//      */
//     @GetMapping("/{ticketId}")
//     public ResponseEntity<Ticket> getTicketById(@PathVariable UUID ticketId) {
//         return ResponseEntity.ok(ticketService.getTicketById(ticketId));
//     }


//     // /**
//     //  * Récupère tous les tickets.
//     //  *
//     //  * @return Une réponse contenant la liste des tickets ou un code 204 si aucun ticket n'est trouvé.
//     //  */
//     // @GetMapping
//     // public ResponseEntity<List<Ticket>> getTickets() {
//     //     List<Ticket> tickets = ticketService.getAllTickets();

//     //     if (tickets == null || tickets.isEmpty()) {
//     //         return ResponseEntity.noContent().build(); // HTTP 204 : Pas de contenu
//     //     }

//     //     return ResponseEntity.ok(tickets); // HTTP 200 : Succès
//     // }


//     // /**
//     //  * Récupère tous les tickets classés par joueur.
//     //  *
//     //  * @return Une liste des tickets groupés par joueur.
//     //  */
//     // @GetMapping("/by-player")
//     // public ResponseEntity<Map<Player, List<Ticket>>> getTicketsGroupedByPlayer() {
//     //     List<Ticket> tickets = ticketService.getAllTickets();

//     //     // Grouper les tickets par joueur
//     //     Map<Player, List<Ticket>> ticketsByPlayer = tickets.stream()
//     //         .collect(Collectors.groupingBy(Ticket::getPlayer));

//     //     return ResponseEntity.ok(ticketsByPlayer);
//     // }


//     /**
//      * Met à jour les informations d'un ticket existant.
//      * @param ticketId ID du ticket à modifier.
//      * @param ticket Objet ticket avec les nouvelles valeurs.
//      * @return Le ticket mis à jour.
//      */
//     @PutMapping("/{ticketId}")
//     public ResponseEntity<Ticket> updateTicket(@PathVariable UUID ticketId, @RequestBody Ticket ticket) {
//         return ResponseEntity.ok(ticketService.updateTicket(ticketId, ticket));
//     }

//     /**
//      * Supprime un ticket spécifique.
//      * @param ticketId ID du ticket à supprimer.
//      */
//     @DeleteMapping("/{ticketId}")
//     public ResponseEntity<Void> deleteTicket(@PathVariable UUID ticketId) {
//         ticketService.deleteTicket(ticketId);
//         return ResponseEntity.noContent().build();
//     }

// // 	// @GetMapping("/{ticketId}/check")
// // 	// public ResponseEntity<String> checkTicket(@PathVariable UUID ticketId) {
// // 	// 	return ResponseEntity.ok(ticketService.checkWinner(ticketId));
// // 	// }


// //     public TicketController(TicketService ticketService) {
// //         this.ticketService = ticketService;
// //     }

// //    /**
// //      * Récupère tous les tickets.
// //      *
// //      * @return Liste de tous les tickets.
// //      */
// //     @GetMapping
// //     public ResponseEntity<List<Ticket>> getTickets() {
// //         List<Ticket> tickets = ticketService.getAllTickets();
// //         return ResponseEntity.ok(tickets);
// //     }

//     // /**
//     //  * Récupère tous les tickets classés par joueur.
//     //  *
//     //  * @return Une liste des tickets groupés par joueur.
//     //  */
//     // @GetMapping("/by-player")
//     // public ResponseEntity<Map<String, List<Ticket>>> getTicketsGroupedByPlayer() {
//     //     List<Ticket> tickets = ticketService.getAllTickets();

//     //     // Grouper les tickets par le nom du joueur
//     //     Map<String, List<Ticket>> ticketsByPlayer = tickets.stream()
//     //         .collect(Collectors.groupingBy(ticket -> ticket.getPlayer().getName()));

//     //     return ResponseEntity.ok(ticketsByPlayer);
//     // }
// }

// package com.fdjloto.api.controller;

// import com.fdjloto.api.model.Ticket;
// import com.fdjloto.api.service.TicketService;

// import io.swagger.v3.oas.annotations.security.SecurityRequirement;
// import io.swagger.v3.oas.annotations.tags.Tag;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.web.bind.annotation.*;

// import java.security.Principal;
// import java.util.List;
// import java.util.UUID;
// import java.util.stream.Collectors;

// @RestController
// @RequestMapping("/api/tickets")
// @Tag(name = "Ticket Management", description = "Endpoints for managing Tickets for user accounts")
// @SecurityRequirement(name = "BearerAuth")
// @SecurityRequirement(name = "jwtCookieAuth")
// @CrossOrigin(origins = "http://127.0.0.1:5500")
// public class TicketController {

//     @Autowired
//     private TicketService ticketService;

//     /**
//      * Récupère tous les tickets d'un utilisateur donné.
//      * Seul l'utilisateur connecté ou un ADMIN peut accéder aux tickets.
//      * @param userId ID de l'utilisateur.
//      * @param principal Utilisateur authentifié.
//      * @return Liste des tickets appartenant à l'utilisateur.
//      */
//     @GetMapping
//     public ResponseEntity<List<Ticket>> getUserTickets(@RequestParam UUID userId, Principal principal) {
//         // 🔐 Vérification de l'utilisateur connecté ou du rôle ADMIN
//         if (principal.getName().equals(userId.toString()) || isAdmin()) {
//             return ResponseEntity.ok(ticketService.getUserTickets(userId));
//         } else {
//             return ResponseEntity.status(403).build();
//         }
//     }

//     // 🔑 Vérification si l'utilisateur est un ADMIN
//     private boolean isAdmin() {
//         return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
//                 .map(GrantedAuthority::getAuthority)
//                 .anyMatch(role -> role.equals("ROLE_ADMIN"));
//     }

//     /**
//      * Crée un nouveau ticket pour un utilisateur.
//      * @param ticket Objet ticket contenant les informations.
//      * @return Le ticket créé.
//      */
//     @PostMapping
//     public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
//         return ResponseEntity.ok(ticketService.createTicket(ticket));
//     }

//     /**
//      * Récupère un ticket spécifique par son ID.
//      * Seul le propriétaire du ticket ou un ADMIN peut accéder au ticket.
//      * @param ticketId ID du ticket.
//      * @param principal Utilisateur authentifié.
//      * @return Le ticket correspondant.
//      */
//     @GetMapping("/{ticketId}")
//     public ResponseEntity<Ticket> getTicketById(@PathVariable UUID ticketId, Principal principal) {
//         Ticket ticket = ticketService.getTicketById(ticketId);

//         // 🔐 Vérification du propriétaire ou rôle ADMIN
//         if (principal.getName().equals(ticket.getUserId().toString()) || isAdmin()) {
//             return ResponseEntity.ok(ticket);
//         } else {
//             return ResponseEntity.status(403).build();
//         }
//     }
// }


package com.fdjloto.api.controller;

import com.fdjloto.api.model.Ticket;
import com.fdjloto.api.model.User;  // Import correct
import com.fdjloto.api.repository.UserRepository; // Import correct

import com.fdjloto.api.service.TicketService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;


@RestController
@RequestMapping("/api/tickets")
@Tag(name = "Ticket Management", description = "Endpoints for managing Tickets for user accounts")
@SecurityRequirement(name = "bearerAuth") // 🔐 Ajout de l'authentification JWT
@SecurityRequirement(name = "jwtCookieAuth") // 🔐 Ajout de l'authentification JWT via cookie
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class TicketController {

    private static final Logger logger = LoggerFactory.getLogger(TicketController.class);


    private TicketService ticketService;
    private UserRepository userRepository; // ✅ Ajout de l'injection du repository


    public TicketController(TicketService ticketService, UserRepository userRepository) {
        this.ticketService = ticketService;
        this.userRepository = userRepository;
    }

    /**
     * 🔥 Récupère tous les tickets de l'utilisateur connecté ou tous les tickets pour un ADMIN.
     * - Utilisateur standard : Ne peut voir que ses propres tickets.
     * - ADMIN : Peut voir tous les tickets.
     *
     * @param principal Utilisateur authentifié (détecté automatiquement par Spring Security)
     * @return Liste des tickets visibles par l'utilisateur connecté
     */

    // @PreAuthorize("hasAuthority('ROLE_ADMIN') or principal.name == #userId.toString()")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<Ticket>> getUserTickets(Principal principal) {
        logger.info("🔐 Authentification JWT via cookie pour l'utilisateur : {}", principal.getName());
        String email = principal.getName();

        // 🔐 Si l'utilisateur est ADMIN, il peut voir tous les tickets
        if (isAdmin()) {
            return ResponseEntity.ok(ticketService.getAllTickets());
        }

        // 🔐 Si l'utilisateur n'est pas ADMIN, il ne voit que ses propres tickets
        return ResponseEntity.ok(ticketService.getTicketsByEmail(email));
    }

    // /**
    //  * 🔐 Vérifie si l'utilisateur connecté a le rôle ADMIN.
    //  * @return true si l'utilisateur est ADMIN, sinon false.
    //  */
    // private boolean isAdmin() {
    //     return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
    //             .map(GrantedAuthority::getAuthority)
    //             .anyMatch(role -> role.equals("ROLE_ADMIN"));
    // }


    /**
     * 🔥 Récupère un ticket spécifique par son ID.
     * - Utilisateur standard : Ne peut voir que ses propres tickets.
     * - ADMIN : Peut voir tous les tickets.
     *
     * @param ticketId ID du ticket
     * @param principal Utilisateur authentifié
     * @return Le ticket s'il est accessible par l'utilisateur connecté
     */

    // @PreAuthorize("hasRole('ADMIN')")
    // @PreAuthorize("hasAuthority('ROLE_ADMIN') or principal.name == #userId.toString()")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{ticketId}")
public ResponseEntity<Ticket> getTicketById(@PathVariable String ticketId, Principal principal) {
    if (ticketId == null || ticketId.isEmpty()) {
        logger.error("🚨 L'ID du ticket est vide ou null.");
        return ResponseEntity.badRequest().body(null);
    }

    try {
        // UUID uuid = UUID.fromString(ticketId); // ✅ Conversion sécurisée String → UUID
        // Ticket ticket = ticketService.getTicketById(uuid); // ✅ Récupération correcte
        Ticket ticket = ticketService.getTicketById(ticketId); // Ne pas convertir en UUID


        if (ticket == null) {
            logger.warn("⚠️ Aucun ticket trouvé pour l'ID : {}", ticketId);
            return ResponseEntity.notFound().build();
        }

        String currentUserEmail = principal.getName();
        Optional<User> optionalUser = userRepository.findByEmail(currentUserEmail);

        if (optionalUser.isEmpty()) {
            logger.warn("⛔ Utilisateur non trouvé : {}", currentUserEmail);
            return ResponseEntity.status(403).build();
        }

        User currentUser = optionalUser.get();
        String currentUserId = currentUser.getId(); // ✅ Stocké en String dans SQLite

        if (isAdmin() || (ticket.getUserId() != null && ticket.getUserId().equals(currentUserId))) {
            logger.info("✅ Accès autorisé au ticket.");
            return ResponseEntity.ok(ticket);
        } else {
            logger.warn("⛔ Accès refusé.");
            return ResponseEntity.status(403).build();
        }
    } catch (IllegalArgumentException e) {
        logger.error("🚨 Erreur de conversion de l'ID du ticket : {}", ticketId);
        return ResponseEntity.badRequest().build();
    }
}


    // @GetMapping("/{ticketId}")
    // public ResponseEntity<Ticket> getTicketById(@PathVariable String ticketId, Principal principal) {
    //     if (ticketId == null || ticketId.isEmpty()) {
    //         logger.error("🚨 L'ID du ticket est vide ou null.");
    //         return ResponseEntity.badRequest().body(null);
    //     }

    //     try {
    //         UUID uuid = UUID.fromString(ticketId); // ✅ Conversion sécurisée String → UUID
    //         Ticket ticket = ticketService.getTicketById(uuid);

    //         if (ticket == null) {
    //             logger.warn("⚠️ Aucun ticket trouvé pour l'ID : {}", ticketId);
    //             return ResponseEntity.notFound().build();
    //         }

    //         String currentUserEmail = principal.getName();
    //         Optional<User> optionalUser = userRepository.findByEmail(currentUserEmail);

    //         if (optionalUser.isEmpty()) {
    //             logger.warn("⛔ Utilisateur non trouvé : {}", currentUserEmail);
    //             return ResponseEntity.status(403).build();
    //         }

    //         User currentUser = optionalUser.get();
    //         UUID currentUserId = currentUser.getId();

    //         if (isAdmin() || (ticket.getUserId() != null && currentUserId.equals(ticket.getUserId()))) {
    //             logger.info("✅ Accès autorisé au ticket.");
    //             return ResponseEntity.ok(ticket);
    //         } else {
    //             logger.warn("⛔ Accès refusé.");
    //             return ResponseEntity.status(403).build();
    //         }
    //     } catch (IllegalArgumentException e) {
    //         logger.error("🚨 Erreur de conversion de l'ID du ticket : {}", ticketId);
    //         return ResponseEntity.badRequest().build();
    //     }
    // }



    // private boolean isAdmin() {
    //     return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
    //             .map(GrantedAuthority::getAuthority)
    //             .anyMatch(role -> role.equals("ROLE_ADMIN"));
    // }

    // private boolean isAdmin() {
    //     var auth = SecurityContextHolder.getContext().getAuthentication();
    //     if (auth == null) {
    //         logger.warn("🔴 Aucun utilisateur authentifié !");
    //         return false;
    //     }

    //     logger.info("🔍 Utilisateur connecté : {}", auth.getName());
    //     logger.info("🔍 Rôles détectés : {}", auth.getAuthorities());

    //     return auth.getAuthorities().stream()
    //             .map(GrantedAuthority::getAuthority)
    //             .anyMatch(role -> role.equals("ROLE_ADMIN"));
    // }

    private boolean isAdmin() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return false;
        }

        return auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_ADMIN")); // ✅ Vérification correcte
    }


    /**
     * 🔥 Crée un ticket en liant automatiquement l'ID utilisateur via JWT.
     *
     * @param ticket Objet ticket envoyé par l'utilisateur.
     * @param principal L'utilisateur authentifié (JWT).
     * @return Le ticket créé.
     */
    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket, Principal principal) {
        String userEmail = principal.getName();
        logger.info("🔐 Création d'un ticket pour l'utilisateur : {}", userEmail);

        Optional<User> optionalUser = userRepository.findByEmail(userEmail);
        if (optionalUser.isEmpty()) {
            logger.error("⛔ Utilisateur non trouvé avec l'email : {}", userEmail);
            return ResponseEntity.status(403).build();
        }

        User user = optionalUser.get();
        ticket.setUserId(user.getId()); // ✅ Utilisation correcte de `setUserId`

        Ticket newTicket = ticketService.createTicket(ticket);
        logger.info("✅ Ticket créé avec succès pour l'utilisateur {} avec ID {}", user.getEmail(), user.getId());
        return ResponseEntity.ok(newTicket);
    }


}


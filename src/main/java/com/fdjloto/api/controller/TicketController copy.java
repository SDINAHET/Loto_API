// // // package com.fdjloto.api.controller;

// // // import com.fdjloto.api.model.Ticket;
// // // import com.fdjloto.api.service.TicketService;

// // // import io.swagger.v3.oas.annotations.security.SecurityRequirement;
// // // import io.swagger.v3.oas.annotations.tags.Tag;

// // // import org.springframework.beans.factory.annotation.Autowired;
// // // import org.springframework.http.ResponseEntity;
// // // import org.springframework.web.bind.annotation.*;

// // // import java.util.Map;
// // // import java.util.List;
// // // import java.util.UUID;
// // // import java.util.stream.Collectors;

// // // @RestController
// // // @RequestMapping("/api/tickets")
// // // @Tag(name = "Ticket Management", description = "Endpoints for managing Tickets for user accounts")
// // // @SecurityRequirement(name = "BearerAuth") // 🔐 Ajout de l'authentification JWT
// // // @SecurityRequirement(name = "jwtCookieAuth") // 🔐 Ajout de l'authentification JWT via cookie

// // // @CrossOrigin(origins = "http://127.0.0.1:5500") // 🔥 Autorise CORS pour Live Server
// // // public class TicketController {

// // //     @Autowired
// // //     private TicketService ticketService;

// // //     /**
// // //      * Crée un nouveau ticket pour un utilisateur.
// // //      * @param ticket Objet ticket contenant les informations.
// // //      * @return Le ticket créé.
// // //      */
// // //     @PostMapping
// // //     public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
// // //         return ResponseEntity.ok(ticketService.createTicket(ticket));
// // //     }

// // //     /**
// // //      * Récupère tous les tickets d'un utilisateur donné.
// // //      * @param userId ID de l'utilisateur.
// // //      * @return Liste des tickets appartenant à l'utilisateur.
// // //      */
// // // 	@GetMapping
// // // 	public ResponseEntity<List<Ticket>> getUserTickets(@RequestParam UUID userId) {
// // // 		return ResponseEntity.ok(ticketService.getUserTickets(userId));
// // // 	}


// // //     /**
// // //      * Récupère un ticket spécifique par son ID.
// // //      * @param ticketId ID du ticket.
// // //      * @return Le ticket correspondant.
// // //      */
// // //     @GetMapping("/{ticketId}")
// // //     public ResponseEntity<Ticket> getTicketById(@PathVariable UUID ticketId) {
// // //         return ResponseEntity.ok(ticketService.getTicketById(ticketId));
// // //     }


// // //     // /**
// // //     //  * Récupère tous les tickets.
// // //     //  *
// // //     //  * @return Une réponse contenant la liste des tickets ou un code 204 si aucun ticket n'est trouvé.
// // //     //  */
// // //     // @GetMapping
// // //     // public ResponseEntity<List<Ticket>> getTickets() {
// // //     //     List<Ticket> tickets = ticketService.getAllTickets();

// // //     //     if (tickets == null || tickets.isEmpty()) {
// // //     //         return ResponseEntity.noContent().build(); // HTTP 204 : Pas de contenu
// // //     //     }

// // //     //     return ResponseEntity.ok(tickets); // HTTP 200 : Succès
// // //     // }


// // //     // /**
// // //     //  * Récupère tous les tickets classés par joueur.
// // //     //  *
// // //     //  * @return Une liste des tickets groupés par joueur.
// // //     //  */
// // //     // @GetMapping("/by-player")
// // //     // public ResponseEntity<Map<Player, List<Ticket>>> getTicketsGroupedByPlayer() {
// // //     //     List<Ticket> tickets = ticketService.getAllTickets();

// // //     //     // Grouper les tickets par joueur
// // //     //     Map<Player, List<Ticket>> ticketsByPlayer = tickets.stream()
// // //     //         .collect(Collectors.groupingBy(Ticket::getPlayer));

// // //     //     return ResponseEntity.ok(ticketsByPlayer);
// // //     // }


// // //     /**
// // //      * Met à jour les informations d'un ticket existant.
// // //      * @param ticketId ID du ticket à modifier.
// // //      * @param ticket Objet ticket avec les nouvelles valeurs.
// // //      * @return Le ticket mis à jour.
// // //      */
// // //     @PutMapping("/{ticketId}")
// // //     public ResponseEntity<Ticket> updateTicket(@PathVariable UUID ticketId, @RequestBody Ticket ticket) {
// // //         return ResponseEntity.ok(ticketService.updateTicket(ticketId, ticket));
// // //     }

// // //     /**
// // //      * Supprime un ticket spécifique.
// // //      * @param ticketId ID du ticket à supprimer.
// // //      */
// // //     @DeleteMapping("/{ticketId}")
// // //     public ResponseEntity<Void> deleteTicket(@PathVariable UUID ticketId) {
// // //         ticketService.deleteTicket(ticketId);
// // //         return ResponseEntity.noContent().build();
// // //     }

// // // // 	// @GetMapping("/{ticketId}/check")
// // // // 	// public ResponseEntity<String> checkTicket(@PathVariable UUID ticketId) {
// // // // 	// 	return ResponseEntity.ok(ticketService.checkWinner(ticketId));
// // // // 	// }


// // // //     public TicketController(TicketService ticketService) {
// // // //         this.ticketService = ticketService;
// // // //     }

// // // //    /**
// // // //      * Récupère tous les tickets.
// // // //      *
// // // //      * @return Liste de tous les tickets.
// // // //      */
// // // //     @GetMapping
// // // //     public ResponseEntity<List<Ticket>> getTickets() {
// // // //         List<Ticket> tickets = ticketService.getAllTickets();
// // // //         return ResponseEntity.ok(tickets);
// // // //     }

// // //     // /**
// // //     //  * Récupère tous les tickets classés par joueur.
// // //     //  *
// // //     //  * @return Une liste des tickets groupés par joueur.
// // //     //  */
// // //     // @GetMapping("/by-player")
// // //     // public ResponseEntity<Map<String, List<Ticket>>> getTicketsGroupedByPlayer() {
// // //     //     List<Ticket> tickets = ticketService.getAllTickets();

// // //     //     // Grouper les tickets par le nom du joueur
// // //     //     Map<String, List<Ticket>> ticketsByPlayer = tickets.stream()
// // //     //         .collect(Collectors.groupingBy(ticket -> ticket.getPlayer().getName()));

// // //     //     return ResponseEntity.ok(ticketsByPlayer);
// // //     // }
// // // }

// // // package com.fdjloto.api.controller;

// // // import com.fdjloto.api.model.Ticket;
// // // import com.fdjloto.api.service.TicketService;

// // // import io.swagger.v3.oas.annotations.security.SecurityRequirement;
// // // import io.swagger.v3.oas.annotations.tags.Tag;

// // // import org.springframework.beans.factory.annotation.Autowired;
// // // import org.springframework.http.ResponseEntity;
// // // import org.springframework.security.core.context.SecurityContextHolder;
// // // import org.springframework.security.core.GrantedAuthority;
// // // import org.springframework.web.bind.annotation.*;

// // // import java.security.Principal;
// // // import java.util.List;
// // // import java.util.UUID;
// // // import java.util.stream.Collectors;

// // // @RestController
// // // @RequestMapping("/api/tickets")
// // // @Tag(name = "Ticket Management", description = "Endpoints for managing Tickets for user accounts")
// // // @SecurityRequirement(name = "BearerAuth")
// // // @SecurityRequirement(name = "jwtCookieAuth")
// // // @CrossOrigin(origins = "http://127.0.0.1:5500")
// // // public class TicketController {

// // //     @Autowired
// // //     private TicketService ticketService;

// // //     /**
// // //      * Récupère tous les tickets d'un utilisateur donné.
// // //      * Seul l'utilisateur connecté ou un ADMIN peut accéder aux tickets.
// // //      * @param userId ID de l'utilisateur.
// // //      * @param principal Utilisateur authentifié.
// // //      * @return Liste des tickets appartenant à l'utilisateur.
// // //      */
// // //     @GetMapping
// // //     public ResponseEntity<List<Ticket>> getUserTickets(@RequestParam UUID userId, Principal principal) {
// // //         // 🔐 Vérification de l'utilisateur connecté ou du rôle ADMIN
// // //         if (principal.getName().equals(userId.toString()) || isAdmin()) {
// // //             return ResponseEntity.ok(ticketService.getUserTickets(userId));
// // //         } else {
// // //             return ResponseEntity.status(403).build();
// // //         }
// // //     }

// // //     // 🔑 Vérification si l'utilisateur est un ADMIN
// // //     private boolean isAdmin() {
// // //         return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
// // //                 .map(GrantedAuthority::getAuthority)
// // //                 .anyMatch(role -> role.equals("ROLE_ADMIN"));
// // //     }

// // //     /**
// // //      * Crée un nouveau ticket pour un utilisateur.
// // //      * @param ticket Objet ticket contenant les informations.
// // //      * @return Le ticket créé.
// // //      */
// // //     @PostMapping
// // //     public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
// // //         return ResponseEntity.ok(ticketService.createTicket(ticket));
// // //     }

// // //     /**
// // //      * Récupère un ticket spécifique par son ID.
// // //      * Seul le propriétaire du ticket ou un ADMIN peut accéder au ticket.
// // //      * @param ticketId ID du ticket.
// // //      * @param principal Utilisateur authentifié.
// // //      * @return Le ticket correspondant.
// // //      */
// // //     @GetMapping("/{ticketId}")
// // //     public ResponseEntity<Ticket> getTicketById(@PathVariable UUID ticketId, Principal principal) {
// // //         Ticket ticket = ticketService.getTicketById(ticketId);

// // //         // 🔐 Vérification du propriétaire ou rôle ADMIN
// // //         if (principal.getName().equals(ticket.getUserId().toString()) || isAdmin()) {
// // //             return ResponseEntity.ok(ticket);
// // //         } else {
// // //             return ResponseEntity.status(403).build();
// // //         }
// // //     }
// // // }


// // package com.fdjloto.api.controller;

// // import com.fdjloto.api.model.Ticket;
// // import com.fdjloto.api.service.TicketService;

// // import io.swagger.v3.oas.annotations.security.SecurityRequirement;
// // import io.swagger.v3.oas.annotations.tags.Tag;

// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.http.ResponseEntity;
// // import org.springframework.security.access.prepost.PreAuthorize;
// // import org.springframework.security.core.GrantedAuthority;
// // import org.springframework.security.core.context.SecurityContextHolder;
// // import org.springframework.web.bind.annotation.*;

// // import java.security.Principal;
// // import java.util.List;
// // import java.util.UUID;
// // import java.util.stream.Collectors;
// // import org.slf4j.Logger;
// // import org.slf4j.LoggerFactory;

// // @RestController
// // @RequestMapping("/api/tickets")
// // @Tag(name = "Ticket Management", description = "Endpoints for managing Tickets for user accounts")
// // @SecurityRequirement(name = "bearerAuth") // 🔐 Ajout de l'authentification JWT
// // @SecurityRequirement(name = "jwtCookieAuth") // 🔐 Ajout de l'authentification JWT via cookie
// // @CrossOrigin(origins = "http://127.0.0.1:5500")
// // public class TicketController {

// //     private static final Logger logger = LoggerFactory.getLogger(TicketController.class);


// //     @Autowired
// //     private TicketService ticketService;

// //     /**
// //      * 🔥 Récupère tous les tickets de l'utilisateur connecté ou tous les tickets pour un ADMIN.
// //      * - Utilisateur standard : Ne peut voir que ses propres tickets.
// //      * - ADMIN : Peut voir tous les tickets.
// //      *
// //      * @param principal Utilisateur authentifié (détecté automatiquement par Spring Security)
// //      * @return Liste des tickets visibles par l'utilisateur connecté
// //      */

// //     // @PreAuthorize("hasAuthority('ROLE_ADMIN') or principal.name == #userId.toString()")
// //     @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
// //     @GetMapping
// //     public ResponseEntity<List<Ticket>> getUserTickets(Principal principal) {
// //         logger.info("🔐 Authentification JWT via cookie pour l'utilisateur : {}", principal.getName());
// //         String email = principal.getName();

// //         // 🔐 Si l'utilisateur est ADMIN, il peut voir tous les tickets
// //         if (isAdmin()) {
// //             return ResponseEntity.ok(ticketService.getAllTickets());
// //         }

// //         // 🔐 Si l'utilisateur n'est pas ADMIN, il ne voit que ses propres tickets
// //         return ResponseEntity.ok(ticketService.getTicketsByEmail(email));
// //     }

// //     // /**
// //     //  * 🔐 Vérifie si l'utilisateur connecté a le rôle ADMIN.
// //     //  * @return true si l'utilisateur est ADMIN, sinon false.
// //     //  */
// //     // private boolean isAdmin() {
// //     //     return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
// //     //             .map(GrantedAuthority::getAuthority)
// //     //             .anyMatch(role -> role.equals("ROLE_ADMIN"));
// //     // }


// //     /**
// //      * 🔥 Récupère un ticket spécifique par son ID.
// //      * - Utilisateur standard : Ne peut voir que ses propres tickets.
// //      * - ADMIN : Peut voir tous les tickets.
// //      *
// //      * @param ticketId ID du ticket
// //      * @param principal Utilisateur authentifié
// //      * @return Le ticket s'il est accessible par l'utilisateur connecté
// //      */

// //     // @PreAuthorize("hasRole('ADMIN')")
// //     // @PreAuthorize("hasAuthority('ROLE_ADMIN') or principal.name == #userId.toString()")
// //     @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
// //     // @GetMapping("/{ticketId}")
// //     // public ResponseEntity<Ticket> getTicketById(@PathVariable UUID ticketId, Principal principal) {
// //     //     Ticket ticket = ticketService.getTicketById(ticketId);

// //     //     // 🔐 Vérification du propriétaire ou du rôle ADMIN
// //     //     if (principal.getName().equals(ticket.getEmail()) || isAdmin()) {
// //     //         return ResponseEntity.ok(ticket);
// //     //     } else {
// //     //         return ResponseEntity.status(403).build(); // 🔴 Accès refusé
// //     //     }
// //     // }
// //     @GetMapping("/{ticketId}")
// //     public ResponseEntity<Ticket> getTicketById(@PathVariable UUID ticketId, Principal principal) {
// //         Ticket ticket = ticketService.getTicketById(ticketId);

// //     //     // 🔐 Vérification du propriétaire ou du rôle ADMIN
// //     //     if (principal.getName().equals(ticket.getUserEmail()) || isAdmin()) {
// //     //         return ResponseEntity.ok(ticket);
// //     //     } else {
// //     //         return ResponseEntity.status(403).build(); // 🔴 Accès refusé
// //     //     }
// //     // }

// //         // // 🔐 Vérification du propriétaire ou du rôle ADMIN
// //         // if (isAdmin() || principal.getName().equals(ticket.getUserEmail())) {
// //         //     return ResponseEntity.ok(ticket);
// //         // } else {
// //         //     return ResponseEntity.status(403).build(); // 🔴 Accès refusé
// //         // }

// //         // ✅ Correction : Vérification du principal avec des logs
// //         logger.info("🔐 Utilisateur connecté : {}", principal.getName());
// //         logger.info("🎫 Email du propriétaire du ticket : {}", ticket.getUserEmail());

// //         // 🔐 Vérification du rôle ADMIN ou si l'utilisateur est propriétaire du ticket
// //         if (isAdmin() || principal.getName().equals(ticket.getUser().getEmail())) {
// //             return ResponseEntity.ok(ticket);
// //         } else {
// //             logger.warn("⛔ Accès refusé : Utilisateur non autorisé.");
// //             return ResponseEntity.status(403).build(); // 🔴 Accès refusé
// //         }
// //     }

// //      /**
// //      * 🔐 Vérifie si l'utilisateur connecté a le rôle ADMIN.
// //      * @return true si l'utilisateur est ADMIN, sinon false.
// //      */
// //     private boolean isAdmin() {
// //         return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
// //                 .map(GrantedAuthority::getAuthority)
// //                 .anyMatch(role -> role.equals("ROLE_ADMIN"));
// //     }


// // }

// // package com.fdjloto.api.controller;

// // import com.fdjloto.api.model.Ticket;
// // import com.fdjloto.api.service.TicketService;
// // import com.fdjloto.api.service.UserService;
// // import com.fdjloto.api.model.User;

// // import io.swagger.v3.oas.annotations.security.SecurityRequirement;
// // import io.swagger.v3.oas.annotations.tags.Tag;
// // import org.springframework.http.ResponseEntity;
// // import org.springframework.security.access.prepost.PreAuthorize;
// // import org.springframework.web.bind.annotation.*;
// // import java.security.Principal;
// // import java.util.List;
// // import java.util.UUID;

// // @RestController
// // @RequestMapping("/api/tickets")
// // @Tag(name = "Ticket Management", description = "Endpoints for managing Tickets")
// // @SecurityRequirement(name = "bearerAuth")
// // @SecurityRequirement(name = "jwtCookieAuth")
// // @CrossOrigin(origins = "http://127.0.0.1:5500")
// // public class TicketController {

// //     private final TicketService ticketService;
// //     private final UserService userService;

// //     public TicketController(TicketService ticketService, UserService userService) {
// //         this.ticketService = ticketService;
// //         this.userService = userService;
// //     }

// //     @PostMapping
// //     @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
// //     public ResponseEntity<Ticket> createTicket(Principal principal) {
// //         String email = principal.getName();
// //         User user = userService.getUserByEmail(email)
// //                 .orElseThrow(() -> new RuntimeException("User not found"));
// //         Ticket ticket = ticketService.createTicket(user.getId());
// //         return ResponseEntity.ok(ticket);
// //     }

// //     @GetMapping
// //     @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
// //     public ResponseEntity<List<Ticket>> getUserTickets(Principal principal) {
// //         String email = principal.getName();
// //         User user = userService.getUserByEmail(email)
// //                 .orElseThrow(() -> new RuntimeException("User not found"));
// //         return ResponseEntity.ok(ticketService.getTicketsByUserId(user.getId()));
// //     }

// //     @GetMapping("/{ticketId}")
// //     @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
// //     public ResponseEntity<Ticket> getTicketById(@PathVariable String ticketId, Principal principal) {
// //         Ticket ticket = ticketService.getTicketById(ticketId);
// //         if (principal.getName().equals(ticket.getUser().getEmail()) || isAdmin(principal)) {
// //             return ResponseEntity.ok(ticket);
// //         } else {
// //             return ResponseEntity.status(403).build();
// //         }
// //     }

// //     @DeleteMapping("/{ticketId}")
// //     @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
// //     public ResponseEntity<Void> deleteTicket(@PathVariable String ticketId, Principal principal) {
// //         String email = principal.getName();
// //         User user = userService.getUserByEmail(email)
// //                 .orElseThrow(() -> new RuntimeException("User not found"));
// //         ticketService.deleteTicket(ticketId, user.getId());
// //         return ResponseEntity.noContent().build();
// //     }

// //     private boolean isAdmin(Principal principal) {
// //         return userService.getUserByEmail(principal.getName())
// //                 .map(User::isAdmin)
// //                 .orElse(false);
// //     }
// // }


// // package com.fdjloto.api.controller;

// // import com.fdjloto.api.dto.TicketDTO;
// // import com.fdjloto.api.model.Ticket;
// // import com.fdjloto.api.model.User;
// // import com.fdjloto.api.security.JwtUtils;
// // import com.fdjloto.api.service.TicketService;
// // import com.fdjloto.api.service.UserService;

// // import io.swagger.v3.oas.annotations.security.SecurityRequirement;
// // import io.swagger.v3.oas.annotations.tags.Tag;
// // import org.springframework.http.ResponseEntity;
// // import org.springframework.security.access.prepost.PreAuthorize;
// // import org.springframework.web.bind.annotation.*;

// // import jakarta.servlet.http.HttpServletRequest;
// // import java.util.List;
// // import java.util.Optional;
// // import java.util.Arrays;
// // import java.util.stream.Collectors; // 🔥 Import manquant



// // @RestController
// // @RequestMapping("/api/tickets")
// // @Tag(name = "Ticket Management", description = "Endpoints for managing Tickets")
// // @SecurityRequirement(name = "bearerAuth")
// // @SecurityRequirement(name = "jwtCookieAuth")
// // @CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true")
// // public class TicketController {

// //     private final TicketService ticketService;
// //     private final UserService userService;
// //     private final JwtUtils jwtUtils;
// //     private static final String USER_NOT_FOUND = "User not found";

// //     public TicketController(TicketService ticketService, UserService userService, JwtUtils jwtUtils) {
// //         this.ticketService = ticketService;
// //         this.userService = userService;
// //         this.jwtUtils = jwtUtils;
// //     }

// //     /**
// //      * 🔥 Récupère tous les tickets du joueur (via JWT Cookie).
// //      * - User : récupère **ses propres tickets**.
// //      * - Admin : récupère **tous les tickets**.
// //      */
// //     // @GetMapping
// //     // @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
// //     // public ResponseEntity<List<Ticket>> getUserTickets(HttpServletRequest request) {
// //     //     // 🔑 Lecture du JWT depuis le cookie
// //     //     Optional<String> jwtOpt = getJwtFromCookie(request);
// //     //     if (jwtOpt.isEmpty()) {
// //     //         return ResponseEntity.status(403).build(); // ⛔ Accès refusé
// //     //     }
// //     //     String jwt = jwtOpt.get();

// //     //     // 🔑 Extraction de l'email depuis le JWT
// //     //     String email = jwtUtils.getUserFromJwtToken(jwt);
// //     //     User user = userService.getUserByEmail(email)
// //     //             .orElseThrow(() -> new RuntimeException("User not found"));

// //     //     // 🔥 Si ADMIN, retourne tous les tickets
// //     //     if (user.isAdmin()) {
// //     //         return ResponseEntity.ok(ticketService.getAllTickets());
// //     //     }

// //     //     // 🔥 Si USER, retourne uniquement ses tickets
// //     //     return ResponseEntity.ok(ticketService.getTicketsByUserId(user.getId()));
// //     // }

// //     /**
// //      * 🔥 Récupère un ticket spécifique (via JWT Cookie).
// //      * - User : ne peut voir que **ses propres tickets**.
// //      * - Admin : peut voir **tous les tickets**.
// //      */
// //     @GetMapping("/{ticketId}")
// //     @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
// //     public ResponseEntity<TicketDTO> getTicketById(@PathVariable String ticketId, HttpServletRequest request) {
// //         Optional<String> jwtOpt = getJwtFromCookie(request);
// //         if (jwtOpt.isEmpty()) {
// //             return ResponseEntity.status(403).build();
// //         }
// //         String jwt = jwtOpt.get();

// //         String email = jwtUtils.getUserFromJwtToken(jwt);
// //         User user = userService.getUserByEmail(email)
// //                 .orElseThrow(() -> new RuntimeException(USER_NOT_FOUND));

// //         Ticket ticket = ticketService.getTicketById(ticketId);

// //         if (user.isAdmin() || ticket.getUser().getId().equals(user.getId())) {
// //             return ResponseEntity.ok(new TicketDTO(ticket));
// //         } else {
// //             return ResponseEntity.status(403).build();
// //         }
// //     }

// //     /**
// //      * 🔥 Création d'un ticket pour l'utilisateur connecté (via JWT Cookie).
// //      */
// //     @GetMapping
// //     @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
// //     public ResponseEntity<List<TicketDTO>> getUserTickets(HttpServletRequest request) {
// //         Optional<String> jwtOpt = getJwtFromCookie(request);
// //         if (jwtOpt.isEmpty()) {
// //             return ResponseEntity.status(403).build();
// //         }
// //         String jwt = jwtOpt.get();
// //         String email = jwtUtils.getUserFromJwtToken(jwt);
// //         User user = userService.getUserByEmail(email)
// //                 .orElseThrow(() -> new RuntimeException("User not found"));

// //         List<TicketDTO> tickets = user.isAdmin()
// //             ? ticketService.getAllTickets().stream().map(ticket -> new TicketDTO(ticket)).toList()
// //             : ticketService.getTicketsByUserId(user.getId()).stream().map(ticket -> new TicketDTO(ticket)).toList();




// //         return ResponseEntity.ok(tickets);
// //     }


// //     /**
// //      * 🔥 Suppression d'un ticket (User = uniquement ses propres tickets, Admin = tous).
// //      */
// //     // @DeleteMapping("/{ticketId}")
// //     // @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
// //     // public ResponseEntity<Void> deleteTicket(@PathVariable String ticketId, HttpServletRequest request) {
// //     //     Optional<String> jwtOpt = getJwtFromCookie(request);
// //     //     if (jwtOpt.isEmpty()) {
// //     //         return ResponseEntity.status(403).build();
// //     //     }
// //     //     String jwt = jwtOpt.get();

// //     //     String email = jwtUtils.getUserFromJwtToken(jwt);
// //     //     User user = userService.getUserByEmail(email)
// //     //             .orElseThrow(() -> new RuntimeException("User not found"));

// //     //     Ticket ticket = ticketService.getTicketById(ticketId);

// //     //     // 🔥 Vérification des droits
// //     //     if (user.isAdmin() || ticket.getUser().getId().equals(user.getId())) {
// //     //         ticketService.deleteTicket(ticketId, user.getId());
// //     //         return ResponseEntity.noContent().build();
// //     //     } else {
// //     //         return ResponseEntity.status(403).build(); // ⛔ Accès refusé
// //     //     }
// //     // }

// //     // @GetMapping
// //     // @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
// //     // public ResponseEntity<List<TicketDTO>> getUserTickets(HttpServletRequest request) {
// //     //     Optional<String> jwtOpt = getJwtFromCookie(request);
// //     //     if (jwtOpt.isEmpty()) {
// //     //         return ResponseEntity.status(403).build();
// //     //     }
// //     //     String jwt = jwtOpt.get();
// //     //     String email = jwtUtils.getUserFromJwtToken(jwt);
// //     //     User user = userService.getUserByEmail(email)
// //     //             .orElseThrow(() -> new RuntimeException("User not found"));

// //     //     List<TicketDTO> tickets = user.isAdmin() ? ticketService.getAllTickets() : ticketService.getTicketsByUserId(user.getId());

// //     //     return ResponseEntity.ok(tickets);
// //     // }

// //     // @GetMapping
// //     // @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
// //     // public ResponseEntity<List<TicketDTO>> getUserTickets(HttpServletRequest request) {
// //     //     Optional<String> jwtOpt = getJwtFromCookie(request);
// //     //     if (jwtOpt.isEmpty()) {
// //     //         return ResponseEntity.status(403).build();
// //     //     }
// //     //     String jwt = jwtOpt.get();
// //     //     String email = jwtUtils.getUserFromJwtToken(jwt);
// //     //     User user = userService.getUserByEmail(email)
// //     //             .orElseThrow(() -> new RuntimeException("User not found"));

// //     //     List<TicketDTO> tickets = user.isAdmin()
// //     //             ? ticketService.getAllTickets().stream().map(TicketDTO::new).collect(Collectors.toList())
// //     //             : ticketService.getTicketsByUserId(user.getId()).stream().map(TicketDTO::new).collect(Collectors.toList());

// //     //     return ResponseEntity.ok(tickets);
// //     // }




// //     /**
// //      * 🔑 Récupère le JWT depuis le cookie "jwtToken".
// //      */
// //     private Optional<String> getJwtFromCookie(HttpServletRequest request) {
// //         if (request.getCookies() != null) {
// //             return Arrays.stream(request.getCookies())
// //                     .filter(cookie -> "jwtToken".equals(cookie.getName()))
// //                     .map(cookie -> cookie.getValue())
// //                     .findFirst();
// //         }
// //         return Optional.empty();
// //     }
// // }

// package com.fdjloto.api.controller;

// import com.fdjloto.api.dto.TicketDTO;
// import com.fdjloto.api.model.Ticket;
// import com.fdjloto.api.model.User;
// import com.fdjloto.api.security.JwtUtils;
// import com.fdjloto.api.service.TicketService;
// import com.fdjloto.api.service.UserService;

// import io.swagger.v3.oas.annotations.security.SecurityRequirement;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.*;

// import jakarta.servlet.http.HttpServletRequest;
// import java.util.List;
// import java.util.Optional;
// import java.time.LocalDate;
// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;
// import java.util.Arrays;
// import com.fdjloto.api.repository.TicketRepository;
// import org.springframework.beans.factory.annotation.Autowired;


// @RestController
// @RequestMapping("/api/tickets")
// @Tag(name = "Ticket Management", description = "Endpoints for managing Tickets")
// @SecurityRequirement(name = "bearerAuth")
// @SecurityRequirement(name = "jwtCookieAuth")
// @CrossOrigin(
//     origins = "http://127.0.0.1:5500",
//     allowCredentials = "true",
//     allowedHeaders = "*",
//     methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
// )
// // @CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true")
// public class TicketController {

//     private final TicketService ticketService;
//     private final UserService userService;
//     private final JwtUtils jwtUtils;
//     private static final String USER_NOT_FOUND = "User not found";

//     public TicketController(TicketService ticketService, UserService userService, JwtUtils jwtUtils) {
//         this.ticketService = ticketService;
//         this.userService = userService;
//         this.jwtUtils = jwtUtils;
//     }
//     @Autowired
//     private TicketRepository ticketRepository;


//     /**
//      * 🔥 Récupère tous les tickets du joueur (via JWT Cookie).
//      * - User : récupère **ses propres tickets**.
//      * - Admin : récupère **tous les tickets**.
//      */
//     @GetMapping
//     @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
//     public ResponseEntity<List<TicketDTO>> getUserTickets(HttpServletRequest request) {
//         Optional<String> jwtOpt = getJwtFromCookie(request);
//         if (jwtOpt.isEmpty()) {
//             return ResponseEntity.status(403).build();
//         }
//         String jwt = jwtOpt.get();
//         String email = jwtUtils.getUserFromJwtToken(jwt);
//         User user = userService.getUserByEmail(email)
//                 .orElseThrow(() -> new RuntimeException(USER_NOT_FOUND));

//         // 🔥 Admin → tous les tickets | User → seulement ses tickets
//         List<TicketDTO> tickets = user.isAdmin()
//             ? ticketService.getAllTickets()
//             : ticketService.getTicketsByUserId(user.getId());


//         return ResponseEntity.ok(tickets);
//     }

//     /**
//      * 🔥 Récupère un ticket spécifique (via JWT Cookie).
//      * - User : ne peut voir que **ses propres tickets**.
//      * - Admin : peut voir **tous les tickets**.
//      */
//     @GetMapping("/{ticketId}")
//     @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
//     public ResponseEntity<TicketDTO> getTicketById(@PathVariable String ticketId, HttpServletRequest request) {
//         Optional<String> jwtOpt = getJwtFromCookie(request);
//         if (jwtOpt.isEmpty()) {
//             return ResponseEntity.status(403).build();
//         }
//         String jwt = jwtOpt.get();
//         String email = jwtUtils.getUserFromJwtToken(jwt);
//         User user = userService.getUserByEmail(email)
//                 .orElseThrow(() -> new RuntimeException(USER_NOT_FOUND));

//         Ticket ticket = ticketService.getTicketById(ticketId);

//         if (user.isAdmin() || ticket.getUser().getId().equals(user.getId())) {
//             return ResponseEntity.ok(new TicketDTO(ticket));
//         } else {
//             return ResponseEntity.status(403).build();
//         }
//     }

//     /**
//      * 🔥 Suppression d'un ticket (User = uniquement ses propres tickets, Admin = tous).
//      */
//     @DeleteMapping("/{ticketId}")
//     @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
//     public ResponseEntity<Void> deleteTicket(@PathVariable String ticketId, HttpServletRequest request) {
//         Optional<String> jwtOpt = getJwtFromCookie(request);
//         if (jwtOpt.isEmpty()) {
//             return ResponseEntity.status(403).build();
//         }
//         String jwt = jwtOpt.get();
//         String email = jwtUtils.getUserFromJwtToken(jwt);
//         User user = userService.getUserByEmail(email)
//                 .orElseThrow(() -> new RuntimeException(USER_NOT_FOUND));

//         Ticket ticket = ticketService.getTicketById(ticketId);

//         if (user.isAdmin() || ticket.getUser().getId().equals(user.getId())) {
//             ticketService.deleteTicket(ticketId, user.getId());
//             return ResponseEntity.noContent().build();
//         } else {
//             return ResponseEntity.status(403).build();
//         }
//     }

//     // @PostMapping
//     // @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
//     // public ResponseEntity<TicketDTO> createTicket(HttpServletRequest request, @RequestBody TicketDTO ticketDTO) {
//     //     Optional<String> jwtOpt = getJwtFromCookie(request);
//     //     if (jwtOpt.isEmpty()) {
//     //         return ResponseEntity.status(403).build();
//     //     }
//     //     String jwt = jwtOpt.get();
//     //     String email = jwtUtils.getUserFromJwtToken(jwt);
//     //     User user = userService.getUserByEmail(email)
//     //             .orElseThrow(() -> new RuntimeException("User not found"));

//     //     Ticket newTicket = ticketService.createTicket(user.getId(), ticketDTO);
//     //     return ResponseEntity.ok(new TicketDTO(newTicket));
//     // }

//     // @PutMapping("/{ticketId}")
//     // @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
//     // public ResponseEntity<TicketDTO> updateTicket(@PathVariable String ticketId, @RequestBody TicketDTO ticketDTO, HttpServletRequest request) {
//     //     Optional<String> jwtOpt = getJwtFromCookie(request);
//     //     if (jwtOpt.isEmpty()) {
//     //         return ResponseEntity.status(403).build();
//     //     }
//     //     String jwt = jwtOpt.get();
//     //     String email = jwtUtils.getUserFromJwtToken(jwt);
//     //     User user = userService.getUserByEmail(email)
//     //             .orElseThrow(() -> new RuntimeException("User not found"));

//     //     Ticket ticket = ticketService.getTicketById(ticketId);
//     //     if (user.isAdmin() || ticket.getUser().getId().equals(user.getId())) {
//     //         Ticket updatedTicket = ticketService.updateTicket(ticketId, ticketDTO);
//     //         return ResponseEntity.ok(new TicketDTO(updatedTicket));
//     //     } else {
//     //         return ResponseEntity.status(403).build(); // ⛔ Accès refusé
//     //     }
//     // }

//     // @DeleteMapping("/{ticketId}")
//     // @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
//     // public ResponseEntity<Void> deleteTicket(@PathVariable String ticketId, HttpServletRequest request) {
//     //     Optional<String> jwtOpt = getJwtFromCookie(request);
//     //     if (jwtOpt.isEmpty()) {
//     //         return ResponseEntity.status(403).build();
//     //     }
//     //     String jwt = jwtOpt.get();
//     //     String email = jwtUtils.getUserFromJwtToken(jwt);
//     //     User user = userService.getUserByEmail(email)
//     //             .orElseThrow(() -> new RuntimeException("User not found"));

//     //     Ticket ticket = ticketService.getTicketById(ticketId);
//     //     if (user.isAdmin() || ticket.getUser().getId().equals(user.getId())) {
//     //         ticketService.deleteTicket(ticketId, user.getId());
//     //         return ResponseEntity.noContent().build();
//     //     } else {
//     //         return ResponseEntity.status(403).build(); // ⛔ Accès refusé
//     //     }
//     // }







//     /**
//      * 🔑 Récupère le JWT depuis le cookie "jwtToken".
//      * Ajoute cette méthode pour récupérer l'utilisateur à partir du JWT dans le cookie
//      */
//     private Optional<String> getJwtFromCookie(HttpServletRequest request) {
//         if (request.getCookies() != null) {
//             return Arrays.stream(request.getCookies())
//                     .filter(cookie -> "jwtToken".equals(cookie.getName()))
//                     .map(cookie -> cookie.getValue())
//                     .findFirst();
//         }
//         return Optional.empty();
//     }

//     @PostMapping
//     @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
//     public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketDTO ticketDTO, HttpServletRequest request) {
//         Optional<String> jwtOpt = getJwtFromCookie(request);
//         if (jwtOpt.isEmpty()) {
//             return ResponseEntity.status(403).body(null);
//         }
//         String jwt = jwtOpt.get();
//         String email = jwtUtils.getUserFromJwtToken(jwt);

//         // 🔍 Recherche de l'utilisateur par email
//         User user = userService.getUserByEmail(email)
//                 .orElseThrow(() -> new RuntimeException("User not found"));

//         // 🔥 Création du ticket pour l'utilisateur connecté
//         Ticket newTicket = ticketService.createTicket(user.getId(), ticketDTO);

//         return ResponseEntity.ok(new TicketDTO(newTicket));
//     }

//     @PutMapping("/{ticketId}")
//     @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
//     public ResponseEntity<TicketDTO> updateTicket(@PathVariable String ticketId, @RequestBody TicketDTO ticketDTO, HttpServletRequest request) {
//         Optional<String> jwtOpt = getJwtFromCookie(request);
//         if (jwtOpt.isEmpty()) {
//             return ResponseEntity.status(403).body(null);
//         }

//         String jwt = jwtOpt.get();
//         String email = jwtUtils.getUserFromJwtToken(jwt);

//         // 🔍 Log pour vérifier l'utilisateur
//         System.out.println("✅ JWT extrait : " + jwt);
//         System.out.println("✅ Email récupéré : " + email);

//         User user = userService.getUserByEmail(email)
//                 .orElseThrow(() -> new RuntimeException("User not found"));

//         // 🔍 Vérification et récupération du ticket
//         Ticket existingTicket = ticketService.getTicketById(ticketId);
//         if (existingTicket == null) {
//             System.out.println("❌ Ticket introuvable avec ID : " + ticketId);
//             return ResponseEntity.status(404).build();
//         }

//         System.out.println("✅ Ticket trouvé : " + existingTicket.getId());

//         // 🔐 Vérification des permissions
//         if (!user.isAdmin() && !existingTicket.getUser().getId().equals(user.getId())) {
//             System.out.println("❌ Accès refusé : l'utilisateur n'est pas propriétaire de ce ticket.");
//             return ResponseEntity.status(403).build();
//         }

//         // 🔥 Mise à jour des champs
//         existingTicket.setNumbers(ticketDTO.getNumbers());

//         try {
//             existingTicket.setChanceNumber(Integer.parseInt(ticketDTO.getChanceNumber()));
//         } catch (NumberFormatException e) {
//             System.out.println("❌ Erreur de conversion du chanceNumber : " + ticketDTO.getChanceNumber());
//             return ResponseEntity.status(400).build();
//         }

//         if (ticketDTO.getDrawDate() != null && !ticketDTO.getDrawDate().isEmpty()) {
//             existingTicket.setDrawDate(LocalDate.parse(ticketDTO.getDrawDate()));
//         }

//         // ✅ Vérification et conversion de updatedAt
//         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//         // if (ticketDTO.getUpdatedAt() != null && !ticketDTO.getUpdatedAt().isEmpty()) {
//         //     try {
//         //         existingTicket.setUpdatedAt(LocalDateTime.parse(ticketDTO.getUpdatedAt(), formatter));
//         //     } catch (Exception e) {
//         //         System.out.println("❌ Erreur de format du champ updatedAt : " + ticketDTO.getUpdatedAt());
//         //         return ResponseEntity.status(400).build();
//         //     }
//         // } else {
//         //     existingTicket.setUpdatedAt(LocalDateTime.now());
//         // }
//         if (ticketDTO.getUpdatedAt() != null && !ticketDTO.getUpdatedAt().isEmpty()) {
//             try {
//                 existingTicket.setUpdatedAt(LocalDateTime.parse(ticketDTO.getUpdatedAt(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
//             } catch (Exception e) {
//                 System.out.println("❌ Erreur de format du champ updatedAt : " + ticketDTO.getUpdatedAt());
//                 return ResponseEntity.status(400).build();
//             }
//         } else {
//             existingTicket.setUpdatedAt(LocalDateTime.now());
//         }


//         // ✅ Sauvegarde et retour de l'objet mis à jour
//         Ticket updatedTicket = ticketRepository.save(existingTicket);
//         System.out.println("✅ Ticket mis à jour : " + updatedTicket.getId());

//         return ResponseEntity.ok(new TicketDTO(updatedTicket));
//     }


// }

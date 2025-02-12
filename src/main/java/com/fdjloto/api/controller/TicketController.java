package com.fdjloto.api.controller;

import com.fdjloto.api.model.Ticket;
import com.fdjloto.api.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    /**
     * Crée un nouveau ticket pour un utilisateur.
     * @param ticket Objet ticket contenant les informations.
     * @return Le ticket créé.
     */
    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        return ResponseEntity.ok(ticketService.createTicket(ticket));
    }

    /**
     * Récupère tous les tickets d'un utilisateur donné.
     * @param userId ID de l'utilisateur.
     * @return Liste des tickets appartenant à l'utilisateur.
     */
	@GetMapping
	public ResponseEntity<List<Ticket>> getUserTickets(@RequestParam UUID userId) {
		return ResponseEntity.ok(ticketService.getUserTickets(userId));
	}


    /**
     * Récupère un ticket spécifique par son ID.
     * @param ticketId ID du ticket.
     * @return Le ticket correspondant.
     */
    @GetMapping("/{ticketId}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable UUID ticketId) {
        return ResponseEntity.ok(ticketService.getTicketById(ticketId));
    }


    // /**
    //  * Récupère tous les tickets.
    //  *
    //  * @return Une réponse contenant la liste des tickets ou un code 204 si aucun ticket n'est trouvé.
    //  */
    // @GetMapping
    // public ResponseEntity<List<Ticket>> getTickets() {
    //     List<Ticket> tickets = ticketService.getAllTickets();

    //     if (tickets == null || tickets.isEmpty()) {
    //         return ResponseEntity.noContent().build(); // HTTP 204 : Pas de contenu
    //     }

    //     return ResponseEntity.ok(tickets); // HTTP 200 : Succès
    // }


    // /**
    //  * Récupère tous les tickets classés par joueur.
    //  *
    //  * @return Une liste des tickets groupés par joueur.
    //  */
    // @GetMapping("/by-player")
    // public ResponseEntity<Map<Player, List<Ticket>>> getTicketsGroupedByPlayer() {
    //     List<Ticket> tickets = ticketService.getAllTickets();

    //     // Grouper les tickets par joueur
    //     Map<Player, List<Ticket>> ticketsByPlayer = tickets.stream()
    //         .collect(Collectors.groupingBy(Ticket::getPlayer));

    //     return ResponseEntity.ok(ticketsByPlayer);
    // }


    /**
     * Met à jour les informations d'un ticket existant.
     * @param ticketId ID du ticket à modifier.
     * @param ticket Objet ticket avec les nouvelles valeurs.
     * @return Le ticket mis à jour.
     */
    @PutMapping("/{ticketId}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable UUID ticketId, @RequestBody Ticket ticket) {
        return ResponseEntity.ok(ticketService.updateTicket(ticketId, ticket));
    }

    /**
     * Supprime un ticket spécifique.
     * @param ticketId ID du ticket à supprimer.
     */
    @DeleteMapping("/{ticketId}")
    public ResponseEntity<Void> deleteTicket(@PathVariable UUID ticketId) {
        ticketService.deleteTicket(ticketId);
        return ResponseEntity.noContent().build();
    }

// 	// @GetMapping("/{ticketId}/check")
// 	// public ResponseEntity<String> checkTicket(@PathVariable UUID ticketId) {
// 	// 	return ResponseEntity.ok(ticketService.checkWinner(ticketId));
// 	// }


//     public TicketController(TicketService ticketService) {
//         this.ticketService = ticketService;
//     }

//    /**
//      * Récupère tous les tickets.
//      *
//      * @return Liste de tous les tickets.
//      */
//     @GetMapping
//     public ResponseEntity<List<Ticket>> getTickets() {
//         List<Ticket> tickets = ticketService.getAllTickets();
//         return ResponseEntity.ok(tickets);
//     }

    // /**
    //  * Récupère tous les tickets classés par joueur.
    //  *
    //  * @return Une liste des tickets groupés par joueur.
    //  */
    // @GetMapping("/by-player")
    // public ResponseEntity<Map<String, List<Ticket>>> getTicketsGroupedByPlayer() {
    //     List<Ticket> tickets = ticketService.getAllTickets();

    //     // Grouper les tickets par le nom du joueur
    //     Map<String, List<Ticket>> ticketsByPlayer = tickets.stream()
    //         .collect(Collectors.groupingBy(ticket -> ticket.getPlayer().getName()));

    //     return ResponseEntity.ok(ticketsByPlayer);
    // }
}

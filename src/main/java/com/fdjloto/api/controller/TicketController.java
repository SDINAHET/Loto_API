package com.fdjloto.api.controller;

import com.fdjloto.api.model.Ticket;
import com.fdjloto.api.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
}

// package com.fdjloto.api.service;

// import com.fdjloto.api.model.Ticket;
// import com.fdjloto.api.repository.TicketRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.Optional;
// import java.util.UUID;

// @Service
// public class TicketService {

//     @Autowired
//     private TicketRepository ticketRepository;

//     /**
//      * Crée un nouveau ticket pour un utilisateur.
//      * @param ticket Objet ticket contenant les informations.
//      * @return Le ticket cr .
//      */
//     public Ticket createTicket(Ticket ticket) {
//         return ticketRepository.save(ticket);
//     }

//     /**
//      * Récupére tous les tickets d'un utilisateur.
//      * @param userId ID de l'utilisateur.
//      * @return La liste des tickets appartenant  l'utilisateur.
//      */
// 	public List<Ticket> getUserTickets(UUID userId) {
// 		return ticketRepository.findByUserId(userId);
// 	}



//     /**
//      * Récupère un ticket spécifique par son ID.
//      * @param ticketId ID du ticket.
//      * @return Le ticket correspondant.
//      * @throws RuntimeException si le ticket n'est pas trouvé.
//      */
//     public Ticket getTicketById(UUID ticketId) {
//         Optional<Ticket> ticket = ticketRepository.findById(ticketId);
//         return ticket.orElseThrow(() -> new RuntimeException("Ticket not found"));
//     }

//     /**
//      * Met à jour les informations d'un ticket existant.
//      * @param ticketId ID du ticket  mettre  jour.
//      * @param updatedTicket Objet ticket contenant les nouvelles valeurs.
//      * @return Le ticket mis  jour.
//      */
//     public Ticket updateTicket(UUID ticketId, Ticket updatedTicket) {
//         Ticket existingTicket = getTicketById(ticketId);
//         existingTicket.setNumbers(updatedTicket.getNumbers());
//         existingTicket.setChanceNumber(updatedTicket.getChanceNumber());
//         return ticketRepository.save(existingTicket);
//     }

//     public List<Ticket> getAllTickets() {
//         return ticketRepository.findAll(); // Assure-toi que `ticketRepository` est bien défini.
//     }


//     /**
//      * Supprime un ticket spécifique par son ID.
//      * @param ticketId ID du ticket à supprimer.
//      */

//     public void deleteTicket(UUID ticketId) {
//         ticketRepository.deleteById(ticketId);
//     }
// }

// package com.fdjloto.api.service;

// import com.fdjloto.api.model.Ticket;
// import com.fdjloto.api.repository.TicketRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.UUID;
// import java.util.Optional;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import com.fdjloto.api.exception.TicketNotFoundException;
// // private static final Logger logger = LoggerFactory.getLogger(TicketService.class);



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
//     // public Ticket getTicketById(UUID ticketId) {
//     //     return ticketRepository.findById(ticketId)
//     //             .orElseThrow(() -> new RuntimeException("Ticket not found"));
//     // }


//     // public Ticket getTicketById(UUID ticketId) {
//     //     return ticketRepository.findById(ticketId)
//     //             .orElseThrow(() -> new TicketNotFoundException("❌ Ticket introuvable avec l'ID : " + ticketId));
//     // }

//     public Ticket getTicketById(UUID ticketId) {
//         String ticketIdString = ticketId.toString(); // 🔥 Convertit UUID en String
//         return ticketRepository.findById(UUID.fromString(ticketIdString))
//                 .orElseThrow(() -> new TicketNotFoundException("❌ Ticket introuvable avec l'ID : " + ticketId));
//     }



// }

package com.fdjloto.api.service;

import com.fdjloto.api.model.Ticket;
import com.fdjloto.api.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository; // ✅ Assure-toi que ce repository existe

    /**
     * 🔥 Crée un ticket et le sauvegarde en base de données.
     * @param ticket Objet Ticket à enregistrer
     * @return Ticket sauvegardé
     */
    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    /**
     * 🔥 Récupère un ticket par son ID
     * @param ticketId ID du ticket
     * @return Ticket trouvé ou null
     */
    // public Ticket getTicketById(UUID ticketId) {
    // ❌ Mauvaise implémentation
    // ticketRepository.findById(ticketId.toString()).orElse(null);

    // ✅ Bonne implémentation
    // public Ticket getTicketById(String ticketId) {
    //     return ticketRepository.findById(ticketId).orElse(null);
    // }

    public Ticket getTicketById(String ticketId) {
        UUID ticketUUID = UUID.fromString(ticketId); // ✅ Conversion correcte
        return ticketRepository.findById(ticketUUID)
                .orElseThrow(() -> new RuntimeException("Ticket introuvable avec l'ID : " + ticketId));
    }



    /**
     * 🔥 Récupère tous les tickets d'un utilisateur par son ID
     * @param userId ID de l'utilisateur
     * @return Liste des tickets de l'utilisateur
     */
    public List<Ticket> getTicketsByUserId(String userId) {
        return ticketRepository.findByUserId(userId);
    }

    /**
     * 🔥 Récupère tous les tickets en base
     * @return Liste des tickets
     */
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    /**
     * 🔥 Récupère les tickets d'un utilisateur via son email
     * @param email Email de l'utilisateur
     * @return Liste des tickets appartenant à cet utilisateur
     */
    public List<Ticket> getTicketsByEmail(String email) {
        return ticketRepository.findByUserEmail(email);
}


}

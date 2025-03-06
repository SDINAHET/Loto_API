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
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import com.fdjloto.api.exception.TicketNotFoundException;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TicketService(TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    public Ticket createTicket(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        return ticketRepository.save(ticket);
    }

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

    public void deleteTicket(String ticketId, String userId) {
        Ticket ticket = getTicketById(ticketId);
        if (!ticket.getUser().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized: You don't own this ticket");
        }
        ticketRepository.deleteById(ticketId);
    }
}

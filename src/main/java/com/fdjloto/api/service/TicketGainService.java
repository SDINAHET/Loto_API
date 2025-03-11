// package com.fdjloto.api.service;

// import com.fdjloto.api.model.Ticket;
// import com.fdjloto.api.model.TicketGain;
// import com.fdjloto.api.repository.TicketGainRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import java.util.List;
// import java.util.Optional;
// import java.util.UUID;

// @Service
// public class TicketGainService {

//     private final TicketGainRepository ticketGainRepository;

//     @Autowired
//     public TicketGainService(TicketGainRepository ticketGainRepository) {
//         this.ticketGainRepository = ticketGainRepository;
//     }

//     /**
//      * Enregistre ou met à jour les gains pour un ticket donné
//      */
//     @Transactional
//     public void saveOrUpdateGain(Ticket ticket, int matchingNumbers, boolean luckyNumberMatch, double gainAmount) {
//         // Vérifie si un gain existe déjà pour ce ticket
//         Optional<TicketGain> existingGain = ticketGainRepository.findById(ticket.getId());

//         if (existingGain.isPresent()) {
//             // Mise à jour du gain existant
//             TicketGain ticketGain = existingGain.get();
//             ticketGain.setMatchingNumbers(matchingNumbers);
//             ticketGain.setLuckyNumberMatch(luckyNumberMatch);
//             ticketGain.setGainAmount(gainAmount);
//             ticketGainRepository.save(ticketGain);
//         } else {
//             // Création d'un nouveau gain
//             TicketGain newTicketGain = new TicketGain(UUID.randomUUID().toString(), ticket, matchingNumbers, luckyNumberMatch, gainAmount);
//             ticketGainRepository.save(newTicketGain);
//         }
//     }

//     /**
//      * Récupère tous les gains enregistrés
//      */
//     public List<TicketGain> getAllGains() {
//         return ticketGainRepository.findAll();
//     }

//     /**
//      * Récupère un gain spécifique par l'ID du ticket
//      */
//     public Optional<TicketGain> getGainByTicketId(String ticketId) {
//         return Optional.ofNullable(ticketGainRepository.findByTicketId(ticketId));
//     }

//     /**
//      * Supprime tous les gains enregistrés (utile avant recalcul)
//      */
//     @Transactional
//     public void deleteAllGains() {
//         ticketGainRepository.deleteAll();
//     }
// }

// package com.fdjloto.api.service;

// import com.fdjloto.api.dto.TicketGainDTO;
// import com.fdjloto.api.model.Ticket;
// import com.fdjloto.api.model.TicketGain;
// import com.fdjloto.api.repository.TicketGainRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
// import java.util.List;
// import java.util.Optional;
// import java.util.UUID;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// @Service
// public class TicketGainService {

//     private static final Logger logger = LoggerFactory.getLogger(TicketGainService.class);
//     private final TicketGainRepository ticketGainRepository;

//     @Autowired
//     public TicketGainService(TicketGainRepository ticketGainRepository) {
//         this.ticketGainRepository = ticketGainRepository;
//     }

//     /**
//      * Enregistre ou met à jour les gains pour un ticket donné
//      */
//     @Transactional
//     public void saveOrUpdateGain(Ticket ticket, int matchingNumbers, boolean luckyNumberMatch, double gainAmount) {
//         logger.info("🔹 Vérification du gain existant pour le ticket ID: {}", ticket.getId());

//         Optional<TicketGain> existingGain = ticketGainRepository.findById(ticket.getId());

//         if (existingGain.isPresent()) {
//             TicketGain ticketGain = existingGain.get();
//             ticketGain.setMatchingNumbers(matchingNumbers);
//             ticketGain.setLuckyNumberMatch(luckyNumberMatch);
//             ticketGain.setGainAmount(gainAmount);
//             ticketGainRepository.save(ticketGain);
//             logger.info("✅ Gain mis à jour pour le ticket ID: {}", ticket.getId());
//         } else {
//             TicketGain newTicketGain = new TicketGain(UUID.randomUUID().toString(), ticket, matchingNumbers, luckyNumberMatch, gainAmount);
//             ticketGainRepository.save(newTicketGain);
//             logger.info("✅ Nouveau gain enregistré pour le ticket ID: {}", ticket.getId());
//         }
//     }

//     /**
//      * Récupère tous les gains enregistrés
//      */
//     public List<TicketGain> getAllGains() {
//         logger.info("🔍 Récupération de tous les gains enregistrés");
//         return ticketGainRepository.findAll();
//     }

//     /**
//      * Récupère un gain spécifique par l'ID du ticket
//      */
//     // public Optional<TicketGain> getGainByTicketId(String ticketId) {
//     //     logger.info("🔍 Recherche du gain pour le ticket ID: {}", ticketId);
//     //     return ticketGainRepository.findByTicketId(ticketId); // ✅ Correction ici
//     // }
//     public TicketGainDTO getGainByTicketId(String ticketId) {
//     TicketGain ticketGain = ticketGainRepository.findByTicketId(ticketId)
//         .orElseThrow(() -> new RuntimeException("Aucun gain trouvé pour le ticket " + ticketId));

//     return new TicketGainDTO(ticketGain);
// }

//     /**
//      * Supprime tous les gains enregistrés (utile avant recalcul)
//      */
//     @Transactional
//     public void deleteAllGains() {
//         logger.warn("⚠️ Suppression de tous les gains enregistrés !");
//         ticketGainRepository.deleteAll();
//     }
// }


// package com.fdjloto.api.service;

// import com.fdjloto.api.dto.TicketGainDTO;
// import com.fdjloto.api.model.Ticket;
// import com.fdjloto.api.model.TicketGain;
// import com.fdjloto.api.repository.TicketGainRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import java.util.List;
// import java.util.Optional;
// import java.util.UUID;

// @Service
// public class TicketGainService {

//     private static final Logger logger = LoggerFactory.getLogger(TicketGainService.class);
//     private final TicketGainRepository ticketGainRepository;

//     @Autowired
//     public TicketGainService(TicketGainRepository ticketGainRepository) {
//         this.ticketGainRepository = ticketGainRepository;
//     }

//     /**
//      * 🔥 Enregistre ou met à jour les gains pour un ticket donné.
//      */
//     @Transactional
//     public void saveOrUpdateGain(Ticket ticket, int matchingNumbers, boolean luckyNumberMatch, double gainAmount) {
//         logger.info("🔹 Vérification du gain existant pour le ticket ID: {}", ticket.getId());

//         Optional<TicketGain> existingGain = ticketGainRepository.findByTicket(ticket);

//         if (existingGain.isPresent()) {
//             TicketGain ticketGain = existingGain.get();
//             ticketGain.setMatchingNumbers(matchingNumbers);
//             ticketGain.setLuckyNumberMatch(luckyNumberMatch);
//             ticketGain.setGainAmount(gainAmount);
//             ticketGainRepository.save(ticketGain);
//             logger.info("✅ Gain mis à jour pour le ticket ID: {}", ticket.getId());
//         } else {
//             TicketGain newTicketGain = new TicketGain(
//                 UUID.randomUUID().toString(), ticket, matchingNumbers, luckyNumberMatch, gainAmount);
//             ticketGainRepository.save(newTicketGain);
//             logger.info("✅ Nouveau gain enregistré pour le ticket ID: {}", ticket.getId());
//         }
//     }

//     /**
//      * 🔍 Récupère tous les gains enregistrés en base.
//      */
//     @Transactional(readOnly = true)
//     public List<TicketGainDTO> getAllGains() {
//         logger.info("🔍 Récupération de tous les gains enregistrés");
//         return ticketGainRepository.findAll()
//             .stream()
//             .map(TicketGainDTO::new) // Conversion en DTO
//             .toList();
//     }

//     /**
//      * 🔍 Récupère un gain spécifique par l'ID du ticket sous forme de DTO.
//      */
//     @Transactional(readOnly = true)
//     public TicketGainDTO getGainByTicketId(String ticketId) {
//         logger.info("🔍 Recherche du gain pour le ticket ID: {}", ticketId);

//         TicketGain ticketGain = ticketGainRepository.findByTicketId(ticketId)
//             .orElseThrow(() -> new RuntimeException("❌ Aucun gain trouvé pour le ticket " + ticketId));

//         return new TicketGainDTO(ticketGain);
//     }

//     /**
//      * ⚠️ Supprime tous les gains enregistrés (utile avant recalcul).
//      */
//     @Transactional
//     public void deleteAllGains() {
//         logger.warn("⚠️ Suppression de tous les gains enregistrés !");
//         ticketGainRepository.deleteAll();
//     }
// }


package com.fdjloto.api.service;

import com.fdjloto.api.dto.TicketGainDTO;
import com.fdjloto.api.model.Ticket;
import com.fdjloto.api.model.TicketGain;
import com.fdjloto.api.repository.TicketGainRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;


@Service
public class TicketGainService {

    private static final Logger logger = LoggerFactory.getLogger(TicketGainService.class);
    private final TicketGainRepository ticketGainRepository;

    public TicketGainService(TicketGainRepository ticketGainRepository) {
        this.ticketGainRepository = ticketGainRepository;
    }

    /**
     * 🔍 Récupère tous les gains et les transforme en DTO.
     */
    public List<TicketGainDTO> getAllGains() {
        logger.info("🔍 Récupération de tous les gains enregistrés");
        return ticketGainRepository.findAll().stream()
                .map(TicketGainDTO::new)
                .toList();
    }

    /**
     * 🎟️ Récupère un gain spécifique par l'ID du ticket et le transforme en DTO.
     */
    public TicketGainDTO getGainByTicketId(String ticketId) {
        TicketGain ticketGain = ticketGainRepository.findByTicketId(ticketId)
                .orElseThrow(() -> new RuntimeException("Aucun gain trouvé pour le ticket " + ticketId));

        return new TicketGainDTO(ticketGain);
    }

    /**
     * ⚠️ Supprime tous les gains enregistrés (utile avant recalcul).
     */
    @Transactional
    public void deleteAllGains() {
        logger.warn("⚠️ Suppression de tous les gains enregistrés !");
        ticketGainRepository.deleteAll();
    }

    /**
     * 🔥 Enregistre ou met à jour les gains pour un ticket donné.
     */
    @Transactional
    public void saveOrUpdateGain(Ticket ticket, int matchingNumbers, boolean luckyNumberMatch, double gainAmount) {
        logger.info("🔹 Vérification du gain existant pour le ticket ID: {}", ticket.getId());

        Optional<TicketGain> existingGain = ticketGainRepository.findById(ticket.getId());

        if (existingGain.isPresent()) {
            TicketGain ticketGain = existingGain.get();
            ticketGain.setMatchingNumbers(matchingNumbers);
            ticketGain.setLuckyNumberMatch(luckyNumberMatch);
            ticketGain.setGainAmount(gainAmount);
            ticketGainRepository.save(ticketGain);
            logger.info("✅ Gain mis à jour pour le ticket ID: {}", ticket.getId());
        } else {
            TicketGain newTicketGain = new TicketGain(ticket.getId(), ticket, matchingNumbers, luckyNumberMatch, gainAmount);
            ticketGainRepository.save(newTicketGain);
            logger.info("✅ Nouveau gain enregistré pour le ticket ID: {}", ticket.getId());
        }
    }
}

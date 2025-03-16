package com.fdjloto.api.service;

import com.fdjloto.api.model.Ticket;
import com.fdjloto.api.model.TicketGain;
import com.fdjloto.api.repository.TicketGainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TicketGainService {

    private final TicketGainRepository ticketGainRepository;

    @Autowired
    public TicketGainService(TicketGainRepository ticketGainRepository) {
        this.ticketGainRepository = ticketGainRepository;
    }

    /**
     * Enregistre ou met à jour les gains pour un ticket donné
     */
    @Transactional
    public void saveOrUpdateGain(Ticket ticket, int matchingNumbers, boolean luckyNumberMatch, double gainAmount) {
        // Vérifie si un gain existe déjà pour ce ticket
        Optional<TicketGain> existingGain = ticketGainRepository.findById(ticket.getId());

        if (existingGain.isPresent()) {
            // Mise à jour du gain existant
            TicketGain ticketGain = existingGain.get();
            ticketGain.setMatchingNumbers(matchingNumbers);
            ticketGain.setLuckyNumberMatch(luckyNumberMatch);
            ticketGain.setGainAmount(gainAmount);
            ticketGainRepository.save(ticketGain);
        } else {
            // Création d'un nouveau gain
            TicketGain newTicketGain = new TicketGain(UUID.randomUUID().toString(), ticket, matchingNumbers, luckyNumberMatch, gainAmount);
            ticketGainRepository.save(newTicketGain);
        }
    }

    /**
     * Récupère tous les gains enregistrés
     */
    public List<TicketGain> getAllGains() {
        return ticketGainRepository.findAll();
    }

    /**
     * Récupère un gain spécifique par l'ID du ticket
    //  */
    // public Optional<TicketGain> getGainByTicketId(String ticketId) {
    //     return Optional.ofNullable(ticketGainRepository.findByTicketId(ticketId));
    // }
    public Optional<TicketGain> getGainByTicketId(String ticketId) {
        return ticketGainRepository.findByTicketId(ticketId); // ✅ CORRECTION
    }


    /**
     * Supprime tous les gains enregistrés (utile avant recalcul)
     */
    @Transactional
    public void deleteAllGains() {
        ticketGainRepository.deleteAll();
    }
}

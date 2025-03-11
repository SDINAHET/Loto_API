package com.fdjloto.api.repository;

import com.fdjloto.api.model.TicketGain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TicketGainRepository extends JpaRepository<TicketGain, String> {
    // TicketGain findByTicketId(String ticketId);
	Optional<TicketGain> findByTicketId(String ticketId); // ✅ Correction : retourne un Optional
}

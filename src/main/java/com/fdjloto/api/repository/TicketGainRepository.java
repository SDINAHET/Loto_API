package com.fdjloto.api.repository;

import com.fdjloto.api.model.TicketGain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketGainRepository extends JpaRepository<TicketGain, String> {
    TicketGain findByTicketId(String ticketId);
}

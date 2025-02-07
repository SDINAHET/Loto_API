package com.fdjloto.api.repository;

import com.fdjloto.api.model.UserTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserTicketRepository extends JpaRepository<UserTicket, Long> {
    List<UserTicket> findByUserId(String userId);
    List<UserTicket> findByTicketId(String ticketId);
}

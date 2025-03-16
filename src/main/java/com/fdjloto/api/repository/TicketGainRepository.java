package com.fdjloto.api.repository;

import com.fdjloto.api.model.TicketGain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository interface for managing **ticket gains** in the database.
 * This interface extends **JpaRepository**, providing built-in CRUD operations.
 */
@Repository
public interface TicketGainRepository extends JpaRepository<TicketGain, String> {

    /**
     * Retrieves the **ticket gain record** associated with a specific ticket ID.
     *
     * @param ticketId The **ID of the ticket** whose gain record is being searched.
     * @return An **Optional** containing the `TicketGain` if found, or **empty** if no record exists.
     */
    Optional<TicketGain> findByTicketId(String ticketId);
}

/**
 * 💡 Explanation:
 * - `TicketGainRepository` is a **JPA repository** responsible for managing ticket gain data.
 * - **`findByTicketId(String ticketId)`** retrieves the gain record for a given ticket.
 * - Returns an **`Optional<TicketGain>`** to handle cases where no gain record exists.
 * - Uses **Spring Data JPA query derivation**, eliminating the need for manual queries.
 */

package com.fdjloto.api.repository;

import com.fdjloto.api.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Repository interface for managing **tickets** in the database.
 * This interface extends **JpaRepository**, providing built-in CRUD operations.
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {

    /**
     * Retrieves all **tickets associated with a given user's email**.
     *
     * @param email The **email** of the user whose tickets are being retrieved.
     * @return A **list of tickets** belonging to the specified user.
     */
    @Query("SELECT t FROM Ticket t WHERE t.user.email = :email")
    List<Ticket> findByUserEmail(@Param("email") String email);

    /**
     * Retrieves all **tickets associated with a given user's ID**.
     *
     * @param userId The **ID of the user** whose tickets are being retrieved.
     * @return A **list of tickets** belonging to the specified user.
     */
    @Query("SELECT t FROM Ticket t WHERE t.user.id = :userId")
    List<Ticket> findByUserId(@Param("userId") String userId);
}

/**
 * 💡 Explanation:
 * - `TicketRepository` is a **JPA repository** that manages ticket data.
 * - The interface **extends JpaRepository<Ticket, String>**, where:
 *   - `Ticket` is the entity.
 *   - `String` is the primary key type (UUID stored as a `String`).
 * - **Custom query methods**:
 *   - `findByUserEmail(String email)`: Retrieves all tickets for a user based on their email.
 *   - `findByUserId(String userId)`: Retrieves all tickets for a user based on their ID.
 * - Uses **JPQL queries (`@Query`)** to fetch data using **explicit joins with the User entity**.
 * - **`@Param` annotation** binds method parameters to named query parameters.
 */

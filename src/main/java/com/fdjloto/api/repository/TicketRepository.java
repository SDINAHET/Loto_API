// package com.fdjloto.api.repository;

// import com.fdjloto.api.model.Ticket;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;


// import java.util.List;
// import java.util.UUID;

// // @Repository
// // public interface TicketRepository extends JpaRepository<Ticket, UUID> {
// //     // List<Ticket> findByUserId(UUID userId);
// //     // 🔥 Recherche des tickets par email
// //     List<Ticket> findByEmail(String email);
// // }

// // TicketRepository.java
// @Repository
// public interface TicketRepository extends JpaRepository<Ticket, UUID> {
//     // 🔥 Recherche des tickets par email en utilisant une jointure
//     @Query("SELECT t FROM Ticket t WHERE t.user.email = :email")
//     List<Ticket> findByUserEmail(@Param("email") String email);


//     // 🔥 Recherche des tickets par user_id en utilisant une jointure
//     @Query("SELECT t FROM Ticket t WHERE t.user.id = :userId")
//     List<Ticket> findByUserId(@Param("userId") String userId);

// }


package com.fdjloto.api.repository;

import com.fdjloto.api.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {
    List<Ticket> findByStatus(String status);

    @Query("SELECT t FROM Ticket t WHERE t.user.email = :email")
    List<Ticket> findByUserEmail(@Param("email") String email);

    @Query("SELECT t FROM Ticket t WHERE t.user.id = :userId")
    List<Ticket> findByUserId(@Param("userId") String userId);
}

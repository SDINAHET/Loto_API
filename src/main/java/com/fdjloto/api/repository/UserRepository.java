package com.fdjloto.api.repository;

import com.fdjloto.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID; // ✅ Ensure UUID import

/**
 * Repository interface for managing **User entities** in the database.
 * This interface extends **JpaRepository**, providing built-in CRUD operations.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> { // ✅ String used instead of UUID for compatibility

    /**
     * Finds a user by their email address.
     *
     * @param email The email of the user to retrieve.
     * @return An **Optional<User>** containing the user if found, otherwise empty.
     */
    Optional<User> findByEmail(String email);
}

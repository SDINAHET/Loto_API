package com.fdjloto.api.repository;

import com.fdjloto.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID; // ✅ Assurer l'import de UUID

@Repository
public interface UserRepository extends JpaRepository<User, UUID> { // ✅ Modifier String → UUID
    Optional<User> findByEmail(String email);
}


package com.fdjloto.api.repository;

import com.fdjloto.api.model.Player;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    // Trouver un joueur par son username
    Optional<Player> findByUsername(String username);

    // Trouver un joueur par son email
    Optional<Player> findByEmail(String email);

    // Trouver un joueur par son ID
    Optional<Player> findById(Long id);

    // Vérifier si un username existe déjà (pour éviter les doublons)
    boolean existsByUsername(String username);

    // Vérifier si un email existe déjà (pour éviter les doublons)
    boolean existsByEmail(String email);
}

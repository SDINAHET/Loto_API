package com.fdjloto.api.service;

import com.fdjloto.api.model.Player;
import com.fdjloto.api.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PasswordEncoder passwordEncoder;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
        this.passwordEncoder = new BCryptPasswordEncoder(); // Utilisation de BCrypt pour hachage sécurisé
    }

    // Création d'un utilisateur
    public Player createPlayer(String username, String email, String password) {
        // Vérifie si l'utilisateur existe déjà par son username ou email
        if (playerRepository.existsByUsername(username)) {
            throw new RuntimeException("Username already exists");
        }
        if (playerRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already exists");
        }

        // Crée un nouvel utilisateur et le sauvegarde dans la base de données
        Player player = new Player();
        player.setUsername(username);
        player.setEmail(email);
        player.setPassword(passwordEncoder.encode(password)); // Hash du mot de passe avant sauvegarde
        return playerRepository.save(player);
    }

    // Vérification du mot de passe
    public boolean verifyPassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword); // Compare le mot de passe brut avec le hachage
    }

    // Récupération de tous les joueurs
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    // Récupération d'un joueur par email
    public Player getPlayerByEmail(String email) {
        return playerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Player not found"));
    }

    // Récupération d'un joueur par username
    public Player getPlayerByUsername(String username) {
        return playerRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Player not found"));
    }
}

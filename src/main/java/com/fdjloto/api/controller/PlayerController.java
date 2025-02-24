package com.fdjloto.api.controller;

import com.fdjloto.api.model.Player;
import com.fdjloto.api.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    // Création d'un joueur - accessible à tous
    @PostMapping
    public ResponseEntity<Object> createPlayer(@RequestBody Map<String, String> requestBody) {
        try {
            String username = requestBody.get("username");
            String email = requestBody.get("email");
            String password = requestBody.get("password");

            Player player = playerService.createPlayer(username, email, password);
            return new ResponseEntity<>(player, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            String errorMessage = e.getMessage();
            if ("Username already exists".equals(errorMessage) || "Email already exists".equals(errorMessage)) {
                return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }

    // Récupération de tous les joueurs - JWT obligatoire avec rôle USER
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<List<Player>> getAllPlayers() {
        List<Player> players = playerService.getAllPlayers();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    // Récupération d'un joueur par email - JWT obligatoire avec rôle USER
    @GetMapping("/email/{email}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Player> getPlayerByEmail(@PathVariable String email) {
        try {
            Player player = playerService.getPlayerByEmail(email);
            return new ResponseEntity<>(player, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retourne 404 si non trouvé
        }
    }
}

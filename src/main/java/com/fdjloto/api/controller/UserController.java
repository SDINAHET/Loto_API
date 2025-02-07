package com.fdjloto.api.controller;

import com.fdjloto.api.model.User;
import com.fdjloto.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User API", description = "Gestion des utilisateurs")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "Récupérer tous les utilisateurs", description = "Retourne la liste de tous les utilisateurs.")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un utilisateur par ID",
               responses = {@ApiResponse(responseCode = "200", description = "Utilisateur trouvé"),
                            @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé")})
    public User getUserById(@PathVariable String id) {
        return userService.getUserById(id).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    @PostMapping("/register")
    @Operation(summary = "Créer un nouvel utilisateur")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        user.setAdmin(false); // ✅ Toujours false par défaut
        User savedUser = userService.saveUser(user); // ✅ Utiliser `userService` au lieu de `userRepository`
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un utilisateur")
    public User updateUser(@PathVariable String id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un utilisateur")
    @ApiResponse(responseCode = "204", description = "Utilisateur supprimé avec succès")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }
}

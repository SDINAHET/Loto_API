package com.fdjloto.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    // @Id
    // @Column(columnDefinition = "TEXT") // SQLite stocke l'UUID en texte
    // @GeneratedValue(strategy = GenerationType.UUID) // ✅ UUID généré automatiquement
    // private String id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // ✅ Génère un UUID natif
    private UUID id; // ✅ Changer String → UUID


    @Size(max = 26, message = "Le prénom ne peut pas dépasser 26 caractères") // ✅ Max 26 caractères
    @NotBlank(message = "Le prénom est obligatoire")
    private String firstName;

    @Size(max = 26, message = "Le nom ne peut pas dépasser 26 caractères") // ✅ Max 26 caractères
    @NotBlank(message = "Le nom est obligatoire")
    private String lastName;

    @Email(message = "L'email doit être valide") // ✅ Vérifie que c'est un email
    @NotBlank(message = "L'email est obligatoire")
    @Column(nullable = false, unique = true)
    private String email;

    @Size(min = 6, message = "Le mot de passe doit contenir au moins 6 caractères") // ✅ Min 6 caractères
    @NotBlank(message = "Le mot de passe est obligatoire")
    @Column(nullable = false)
    private String password;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE") // 🔥 Définit admin = false (0)
    private boolean admin = false;

    // @Column(nullable = true) // ✅ ajout de la colonne token
    // private String token;

    public User() {
        this.id = UUID.randomUUID(); // ✅ Génère un UUID sous forme de String
        this.admin = false; // ✅ Assure que tous les nouveaux utilisateurs sont non-admin
    }

    public User(String firstName, String lastName, String email, String password, boolean admin) {
        this.id = UUID.randomUUID(); // ✅ Génère un UUID sous forme de String
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.admin = admin;
    }

    // // ✅ Constructeur permettant de passer un ID spécifique
    // public User(String id, String firstName, String lastName, String email, String password, boolean admin) {
    //     this.id = id;
    //     this.firstName = firstName;
    //     this.lastName = lastName;
    //     this.email = email;
    //     this.password = password;
    //     this.admin = admin;
    // }

    // ✅ Getters et Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; } // ✅ Utilisation de String au lieu de UUID

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isAdmin() { return admin; } // ✅ Getter correct
    public void setAdmin(boolean admin) { this.admin = admin; } // ✅ Setter correct
}

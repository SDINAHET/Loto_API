// package com.fdjloto.api.model;

// import jakarta.persistence.*;
// import jakarta.validation.constraints.Email;
// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.Size;
// import io.swagger.v3.oas.annotations.media.Schema;


// import java.util.UUID;

// @Schema(description = "Represents a user in the system")
// @Entity
// @Table(name = "users")
// public class User {

//     // @Id
//     // @Column(columnDefinition = "TEXT") // SQLite stocke l'UUID en texte
//     // @GeneratedValue(strategy = GenerationType.UUID) // ✅ UUID généré automatiquement
//     // private String id;

//     // @Id
//     // @GeneratedValue(strategy = GenerationType.AUTO) // ✅ Génère un UUID natif
//     // private UUID id; // ✅ Changer String → UUID

//     @Id
//     @Column(columnDefinition = "TEXT", unique = true, nullable = false) // ✅ Stocker UUID en `TEXT` (String)
//     private String id;

//     @Size(max = 26, message = "Le prénom ne peut pas dépasser 26 caractères") // ✅ Max 26 caractères
//     @NotBlank(message = "Le prénom est obligatoire")
//     private String firstName;

//     @Size(max = 26, message = "Le nom ne peut pas dépasser 26 caractères") // ✅ Max 26 caractères
//     @NotBlank(message = "Le nom est obligatoire")
//     private String lastName;

//     @Email(message = "L'email doit être valide") // ✅ Vérifie que c'est un email
//     @NotBlank(message = "L'email est obligatoire")
//     @Column(nullable = false, unique = true)
//     private String email;

//     @Size(min = 6, message = "Le mot de passe doit contenir au moins 6 caractères") // ✅ Min 6 caractères
//     @NotBlank(message = "Le mot de passe est obligatoire")
//     @Column(nullable = false)
//     private String password;

//     @Schema(defaultValue = "false", description = "Defines if the user has admin privileges")
//     @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE") // 🔥 Définit admin = false (0)
//     private boolean admin = false;

//     // @Column(nullable = true) // ✅ ajout de la colonne token
//     // private String token;

//      /** ✅ Génère automatiquement l'UUID avant l'insertion en base */
//     //  @PrePersist
//     //  public void generateUUID() {
//     //      if (id == null) {
//     //          id = UUID.randomUUID();
//     //      }
//     //      if (!admin) {
//     //          admin = false;
//     //      }
//     //  }

//     /** ✅ Génère automatiquement l'UUID avant l'insertion en base */
//     @PrePersist
//     public void generateUUID() {
//         if (id == null) {
//             id = UUID.randomUUID().toString(); // ✅ Stocke UUID sous forme de String
//         }
//     }

//     public User() {
//         // this.id = UUID.randomUUID(); // ✅ Génère un UUID sous forme de String
//         this.admin = false; // ✅ Assure que tous les nouveaux utilisateurs sont non-admin
//     }

//     public User(String firstName, String lastName, String email, String password, boolean admin) {
//         // this.id = UUID.randomUUID(); // ✅ Génère un UUID sous forme de String
//         this.firstName = firstName;
//         this.lastName = lastName;
//         this.email = email;
//         this.password = password;
//         this.admin = admin;
//     }

//     // // ✅ Constructeur permettant de passer un ID spécifique
//     // public User(String id, String firstName, String lastName, String email, String password, boolean admin) {
//     //     this.id = id;
//     //     this.firstName = firstName;
//     //     this.lastName = lastName;
//     //     this.email = email;
//     //     this.password = password;
//     //     this.admin = admin;
//     // }

//     // ✅ Getters et Setters
//     public String getId() { return id; }
//     public void setId(String id) { this.id = id; } // ✅ Utilisation de String au lieu de UUID

//     public String getFirstName() { return firstName; }
//     public void setFirstName(String firstName) { this.firstName = firstName; }

//     public String getLastName() { return lastName; }
//     public void setLastName(String lastName) { this.lastName = lastName; }

//     public String getEmail() { return email; }
//     public void setEmail(String email) { this.email = email; }

//     public String getPassword() { return password; }
//     public void setPassword(String password) { this.password = password; }

//     public boolean isAdmin() { return admin; } // ✅ Getter correct
//     public void setAdmin(boolean admin) { this.admin = admin; } // ✅ Setter correct

//     // public boolean isAdmin() {
//     //     return this.admin;
//     // }

// }


package com.fdjloto.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;


import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.List;


@Schema(description = "Represents a user in the system")
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(columnDefinition = "TEXT", unique = true, nullable = false)
    private String id;

    @Size(max = 26, message = "Le prénom ne peut pas dépasser 26 caractères")
    @NotBlank(message = "Le prénom est obligatoire")
    private String firstName;

    @Size(max = 26, message = "Le nom ne peut pas dépasser 26 caractères")
    @NotBlank(message = "Le nom est obligatoire")
    private String lastName;

    @Email(message = "L'email doit être valide")
    @NotBlank(message = "L'email est obligatoire")
    @Column(nullable = false, unique = true)
    private String email;

    @Size(min = 6, message = "Le mot de passe doit contenir au moins 6 caractères")
    @NotBlank(message = "Le mot de passe est obligatoire")
    @Column(nullable = false)
    private String password;

    @Schema(defaultValue = "false", description = "Defines if the user has admin privileges")
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean admin;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets;


    /** ✅ Génère automatiquement l'UUID avant l'insertion en base */
    @PrePersist
    public void generateUUID() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }

    public User() {
        this.admin = false; // ✅ Par défaut, les utilisateurs ne sont pas admins
    }

    public User(String firstName, String lastName, String email, String password, boolean admin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.admin = admin;
    }

    // ✅ Getters et Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isAdmin() { return admin; }
    public void setAdmin(boolean admin) { this.admin = admin; }

    // ✅ Getter personnalisé pour Spring Security
    public String getRole() {
        return this.admin ? "ROLE_ADMIN" : "ROLE_USER";
    }
}




// curl -X 'GET' \
//   'http://localhost:8082/api/users' \
//   -H 'accept: */*' \
//   -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0NEBoYm5iLmNvbSIsInJvbGVzIjpbIlJPTEVfQURNSU4iXSwiaWF0IjoxNzQwNzMyNzA4LCJleHAiOjE3NDA4MTkxMDh9.xiN-umsuN1_R7Z5Fu3IBiR2btoqPMSLsfw_NoSjaiP4'

// curl -X 'GET' \
//   'http://localhost:8082/api/tickets?userId=7413b56e-9b24-44db-a0fa-a04b9ef82a31' \
//   -H 'accept: */*'
//   -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0NEBoYm5iLmNvbSIsInJvbGVzIjpbIlJPTEVfQURNSU4iXSwiaWF0IjoxNzQwNzMyNzA4LCJleHAiOjE3NDA4MTkxMDh9.xiN-umsuN1_R7Z5Fu3IBiR2btoqPMSLsfw_NoSjaiP4'

// // package com.fdjloto.api.model;

// // import jakarta.persistence.*;
// // import java.time.LocalDate;
// // import java.util.UUID;
// // import java.util.regex.Pattern;

// // @Entity
// // public class Ticket {

// //     @Id
// //     @GeneratedValue
// //     private UUID id;

// //     // @Column(nullable = false)
// //     // private UUID userId;

// //     @Column(nullable = false)
// //     private String email;  // 🔥 Ajout de l'email pour lier les tickets à l'utilisateur

// //     @Column(nullable = false)
// //     private String numbers; // Format: "1-2-3-4-5"

// //     @Column(nullable = false)
// //     private int chanceNumber = 1; // Par défaut 1, entre 1 et 10

// //     @Column(nullable = false)
// //     private LocalDate drawDate;

// //     // public UUID getId() {
// //     //     return id;
// //     // }

// //     // public void setId(UUID id) {
// //     //     this.id = id;
// //     // }

// //     // public UUID getUserId() {
// //     //     return userId;
// //     // }

// //     // public void setUserId(UUID userId) {
// //     //     this.userId = userId;
// //     // }

// //      // Getters et Setters

// //     public String getEmail() {
// //         return email;
// //     }

// //     public void setEmail(String email) {
// //         this.email = email;
// //     }

// //     public String getNumbers() {
// //         return numbers;
// //     }

// //     public void setNumbers(String numbers) {
// //         if (!isValidNumbers(numbers)) {
// //             throw new IllegalArgumentException("Les numéros doivent être au format '1-2-3-4-5' avec des valeurs entre 1 et 49.");
// //         }
// //         this.numbers = numbers;
// //     }

// //     public int getChanceNumber() {
// //         return chanceNumber;
// //     }

// //     public void setChanceNumber(int chanceNumber) {
// //         if (chanceNumber <= 1 || chanceNumber > 10) {
// //             throw new IllegalArgumentException("Le numéro chance doit être entre 1 et 10.");
// //         }
// //         this.chanceNumber = chanceNumber;
// //     }

// //     public LocalDate getDrawDate() {
// //         return drawDate;
// //     }

// //     public void setDrawDate(LocalDate drawDate) {
// //         this.drawDate = drawDate;
// //     }

// //     private boolean isValidNumbers(String numbers) {
// //         String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
// //         return Pattern.matches(regex, numbers);
// //     }
// // }


// package com.fdjloto.api.model;

// import jakarta.persistence.*;
// import java.time.LocalDate;
// import java.util.UUID;
// import com.fdjloto.api.converter.*;
// import com.fasterxml.jackson.annotation.JsonFormat;

// @Entity
// @Table(name = "tickets")  // Assurez-vous que le nom de la table est bien "ticket"
// public class Ticket {

//     @Id
//     @GeneratedValue
//     private UUID id;

//     @Column(nullable = false)
//     private String numbers;

//     @Column(nullable = false, name = "lucky_number")
//     private int chanceNumber;

//     // @Column(nullable = false, name = "draw_date")
//     // private LocalDate drawDate;

//     @Column(nullable = false, name = "draw_date")
//     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//     @Convert(converter = DateConverter.class)
//     private LocalDate drawDate;

//     // @Column(nullable = false, name = "draw_date")
//     // private String drawDate;


//     @Column(nullable = false, name = "draw_day")
//     private String drawDay;

//     @ManyToOne
//     @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
//     private User user;

//     // Getters et Setters

//     public UUID getId() {
//         return id;
//     }

//     public void setId(UUID id) {
//         this.id = id;
//     }

//     public String getNumbers() {
//         return numbers;
//     }

//     public void setNumbers(String numbers) {
//         this.numbers = numbers;
//     }

//     public int getChanceNumber() {
//         return chanceNumber;
//     }

//     public void setChanceNumber(int chanceNumber) {
//         this.chanceNumber = chanceNumber;
//     }

//     public LocalDate getDrawDate() {
//         return drawDate;
//     }

//     public void setDrawDate(LocalDate drawDate) {
//         this.drawDate = drawDate;
//     }

//     public String getDrawDay() {
//         return drawDay;
//     }

//     public void setDrawDay(String drawDay) {
//         this.drawDay = drawDay;
//     }

//     public User getUser() {
//         return user;
//     }

//     public void setUser(User user) {
//         this.user = user;
//     }

//     public String getUserEmail() {
//         return user.getEmail();
//     }

// }


// package com.fdjloto.api.model;

// import jakarta.persistence.*;
// import java.time.LocalDate;
// import java.time.LocalDateTime;
// import java.util.UUID;
// import com.fasterxml.jackson.annotation.JsonFormat;
// import com.fasterxml.jackson.annotation.JsonIgnore;

// @Entity
// @Table(name = "tickets")
// public class Ticket {

//     @Id
//     @Column(name = "id", columnDefinition = "TEXT") // SQLite ne supporte pas UUID directement
//     private String id;

//     @Column(nullable = false)
//     private String numbers;

//     @Column(nullable = false, name = "lucky_number")
//     private int chanceNumber;

//     @Column(nullable = false, name = "draw_date")
//     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//     private LocalDate drawDate;

//     @Column(nullable = false, name = "draw_day")
//     private String drawDay;

//     @Column(nullable = false, updatable = false)
//     private LocalDateTime createdAt;

//     @ManyToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
//     @JsonIgnore
//     private User user;

//     public Ticket() {
//         this.id = UUID.randomUUID().toString();
//     }

//     // Getters et Setters
//     public String getId() { return id; }
//     public void setId(UUID id) { this.id = id.toString(); }
//     public String getNumbers() { return numbers; }
//     public void setNumbers(String numbers) { this.numbers = numbers; }
//     public int getChanceNumber() { return chanceNumber; }
//     public void setChanceNumber(int chanceNumber) { this.chanceNumber = chanceNumber; }
//     public LocalDate getDrawDate() { return drawDate; }
//     public void setDrawDate(LocalDate drawDate) { this.drawDate = drawDate; }
//     public String getDrawDay() { return drawDay; }
//     public void setDrawDay(String drawDay) { this.drawDay = drawDay; }
//     public User getUser() { return user; }
//     public void setUser(User user) { this.user = user; }
//     public String getUserEmail() { return user.getEmail(); }
//     public LocalDateTime getCreatedAt() { return createdAt; }

//     @PrePersist
//     public void prePersist() {
//         this.createdAt = LocalDateTime.now();
//     } // 🔥✅ Ajout de l'accolade de fermeture manquante

// }


// package com.fdjloto.api.model;

// import jakarta.persistence.*;
// import java.time.LocalDate;
// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;
// import java.util.UUID;
// import com.fasterxml.jackson.annotation.JsonFormat;
// import com.fasterxml.jackson.annotation.JsonIgnore;
// import com.fdjloto.api.converter.LocalDateTimeAttributeConverter;

// @Entity
// @Table(name = "tickets")
// public class Ticket {

//     @Id
//     @Column(name = "id", columnDefinition = "TEXT") // SQLite ne supporte pas UUID directement
//     private String id;

//     @Column(nullable = false)
//     private String numbers;

//     @Column(nullable = false, name = "lucky_number")
//     private int chanceNumber;

//     @Column(nullable = false, name = "draw_date")
//     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//     private LocalDate drawDate;

//     @Column(nullable = true, name = "draw_day")
//     private String drawDay;

//     // // ✅ Nouvelle colonne : statut du ticket (GAGNANT, PERDU, EN ATTENTE)
//     // private String statut = "EN ATTENTE";

//     // // ✅ Nouvelle colonne : montant du gain
//     // private double gain = 0.0;

//     // @Column(nullable = false, updatable = false)
//     // private LocalDateTime createdAt;

//     // @Column(nullable = true) // ✅ `updatedAt` peut être `null` avant la première mise à jour
//     // private LocalDateTime updatedAt;

//     // @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TEXT")
//     // private LocalDateTime createdAt;

//     // @Column(name = "updated_at", nullable = true, columnDefinition = "TEXT")
//     // private LocalDateTime updatedAt;

//     // @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TEXT")
//     // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
//     // private LocalDateTime createdAt;

//     // @Column(name = "updated_at", nullable = true, columnDefinition = "TEXT")
//     // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
//     // private LocalDateTime updatedAt;

//     @Column(name = "created_at")
//     @Convert(converter = LocalDateTimeAttributeConverter.class)
//     private LocalDateTime createdAt;

//     @Column(name = "updated_at")
//     @Convert(converter = LocalDateTimeAttributeConverter.class)
//     private LocalDateTime updatedAt;




//     @ManyToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
//     @JsonIgnore
//     private User user;

//     // ✅ Constructeur : Génère un UUID et initialise `createdAt`
//     public Ticket() {
//         this.id = UUID.randomUUID().toString();
//         this.createdAt = LocalDateTime.now();
//     }

//     // @PrePersist
//     // public void prePersist() {
//     //     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//     //     this.createdAt = LocalDateTime.now().format(formatter);
//     //     this.updatedAt = LocalDateTime.now().format(formatter); // ✅ Initialisation
//     // }

//     // @PreUpdate
//     // public void preUpdate() {
//     //     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//     //     this.updatedAt = LocalDateTime.now().format(formatter); // ✅ Met à jour `updatedAt`
//     // }

//     @PrePersist
//     public void prePersist() {
//         this.createdAt = LocalDateTime.now();
//         this.updatedAt = LocalDateTime.now();
//     }

//     @PreUpdate
//     public void preUpdate() {
//         this.updatedAt = LocalDateTime.now();
//     }

//     // ✅ Getters et Setters
//     public String getId() { return id; }
//     public void setId(UUID id) { this.id = id.toString(); }
//     public String getNumbers() { return numbers; }
//     public void setNumbers(String numbers) { this.numbers = numbers; }
//     public int getChanceNumber() { return chanceNumber; }
//     public void setChanceNumber(int chanceNumber) { this.chanceNumber = chanceNumber; }
//     public LocalDate getDrawDate() { return drawDate; }
//     public void setDrawDate(LocalDate drawDate) { this.drawDate = drawDate; }
//     public String getDrawDay() { return drawDay; }
//     public void setDrawDay(String drawDay) { this.drawDay = drawDay; }
//     public User getUser() { return user; }
//     public void setUser(User user) { this.user = user; }
//     public String getUserEmail() { return user.getEmail(); }
//     public LocalDateTime getCreatedAt() { return createdAt; }
//     public LocalDateTime getUpdatedAt() { return updatedAt; } // ✅ Ajout du getter
//     // public String getStatut() { return statut; }
//     // public void setStatut(String statut) { this.statut = statut; }

//     // public double getGain() { return gain; }
//     // public void setGain(double gain) { this.gain = gain; }

//     // @PrePersist
//     // public void prePersist() {
//     //     this.createdAt = LocalDateTime.now();  // ✅ Définit `created_at` à la date actuelle
//     //     // this.updatedAt = null; // ✅ S'assure que `updated_at` est NULL à la création
//     //     this.updatedAt = LocalDateTime.now();  // ✅ Définit `updated_at` à la date actuelle
//     // }

//     // @PreUpdate
//     // public void preUpdate() {
//     //     this.updatedAt = LocalDateTime.now(); // ✅ Définit `updated_at` uniquement lors d'une mise à jour
//     // }




//     // public void setCreatedAt(LocalDateTime createdAt) {
//     //     this.createdAt = createdAt;
//     // }

//     // public void setUpdatedAt(LocalDateTime updatedAt) {
//     //     this.updatedAt = updatedAt;
//     // }
//     public void setCreatedAt(String createdAt) {
//         this.createdAt = LocalDateTime.parse(createdAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//     }

//     public void setUpdatedAt(String updatedAt) {
//         this.updatedAt = LocalDateTime.parse(updatedAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//     }

//     public void setCreatedAt(LocalDateTime createdAt) {
//         this.createdAt = createdAt;
//     }

//     public void setUpdatedAt(LocalDateTime updatedAt) {
//         this.updatedAt = updatedAt;
//     }






// }

package com.fdjloto.api.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @Column(name = "id", columnDefinition = "TEXT") // SQLite ne supporte pas UUID directement
    private String id;

    @Column(nullable = false)
    private String numbers; // Ex: "12-23-34-45-56"

    @Column(nullable = false, name = "lucky_number")
    private int chanceNumber;

    @Column(nullable = false, name = "draw_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate drawDate;

    @Column(nullable = true, name = "draw_day")
    private String drawDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private User user;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "guains")
    private double gains;

    @Column(name = "status")
    private String status;



    // ✅ Constructeur : Génère un UUID et initialise `createdAt`
    public Ticket() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // ✅ Ajout de la méthode `getNumerosAsList()` pour convertir "12-23-34-45-56" en `List<Integer>`
    public List<Integer> getNumerosAsList() {
        return Arrays.stream(numbers.split("-"))
                     .map(Integer::parseInt)
                     .collect(Collectors.toList());
    }

    // ✅ Getters et Setters
    public String getId() { return id; }
    public void setId(UUID id) { this.id = id.toString(); }

    public String getNumbers() { return numbers; }
    public void setNumbers(String numbers) { this.numbers = numbers; }

    public int getChanceNumber() { return chanceNumber; }
    public void setChanceNumber(int chanceNumber) { this.chanceNumber = chanceNumber; }

    public LocalDate getDrawDate() { return drawDate; }
    public void setDrawDate(LocalDate drawDate) { this.drawDate = drawDate; }

    public String getDrawDay() { return drawDay; }
    public void setDrawDay(String drawDay) { this.drawDay = drawDay; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getUserEmail() { return user.getEmail(); }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public void setGains(double gains) { this.gains = gains; }
    public void setStatus(String status) { this.status = status; }

    // public int getLuckyNumber() { return this.luckyNumber; }
}



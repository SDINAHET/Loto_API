// package com.fdjloto.api.model;

// import jakarta.persistence.*;
// import java.time.LocalDate;
// import java.util.UUID;
// import java.util.regex.Pattern;

// @Entity
// public class Ticket {

//     @Id
//     @GeneratedValue
//     private UUID id;

//     // @Column(nullable = false)
//     // private UUID userId;

//     @Column(nullable = false)
//     private String email;  // 🔥 Ajout de l'email pour lier les tickets à l'utilisateur

//     @Column(nullable = false)
//     private String numbers; // Format: "1-2-3-4-5"

//     @Column(nullable = false)
//     private int chanceNumber = 1; // Par défaut 1, entre 1 et 10

//     @Column(nullable = false)
//     private LocalDate drawDate;

//     // public UUID getId() {
//     //     return id;
//     // }

//     // public void setId(UUID id) {
//     //     this.id = id;
//     // }

//     // public UUID getUserId() {
//     //     return userId;
//     // }

//     // public void setUserId(UUID userId) {
//     //     this.userId = userId;
//     // }

//      // Getters et Setters

//     public String getEmail() {
//         return email;
//     }

//     public void setEmail(String email) {
//         this.email = email;
//     }

//     public String getNumbers() {
//         return numbers;
//     }

//     public void setNumbers(String numbers) {
//         if (!isValidNumbers(numbers)) {
//             throw new IllegalArgumentException("Les numéros doivent être au format '1-2-3-4-5' avec des valeurs entre 1 et 49.");
//         }
//         this.numbers = numbers;
//     }

//     public int getChanceNumber() {
//         return chanceNumber;
//     }

//     public void setChanceNumber(int chanceNumber) {
//         if (chanceNumber <= 1 || chanceNumber > 10) {
//             throw new IllegalArgumentException("Le numéro chance doit être entre 1 et 10.");
//         }
//         this.chanceNumber = chanceNumber;
//     }

//     public LocalDate getDrawDate() {
//         return drawDate;
//     }

//     public void setDrawDate(LocalDate drawDate) {
//         this.drawDate = drawDate;
//     }

//     private boolean isValidNumbers(String numbers) {
//         String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
//         return Pattern.matches(regex, numbers);
//     }
// }


// package com.fdjloto.api.model;

// import jakarta.persistence.*;
// import java.time.LocalDate;
// import java.util.UUID;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.context.SecurityContextHolder;

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

//     // public UUID getUserId() {
//     //     ticket.getUser().getId()
//     // }

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

//     // public User getUser() {
//     //     return user;
//     // }

//     public UUID getUserId() {
//         if (user == null || user.getId() == null) {
//             return null;
//         }
//         return UUID.fromString(user.getId().toString()); // ✅ Conversion assurée
//     }



//     public void setUser(User user) {
//         this.user = user;
//     }

//     // public String getUserEmail() {
//     //     return user.getEmail(); //Si user est null, ton application plantera avec une NullPointerException.
//     // }

//     public String getUserEmail() {
//         return user != null ? user.getEmail() : null;
//     }



// }

// package com.fdjloto.api.model;

// import jakarta.persistence.*;
// import java.time.LocalDate;
// import java.util.UUID;
// import com.fdjloto.api.converter.UUIDConverter;
// import com.fasterxml.jackson.annotation.JsonFormat;
// import java.util.regex.Pattern;


// @Entity
// @Table(name = "tickets")
// public class Ticket {

//     // @Id
//     // @GeneratedValue
//     // @Convert(converter = UUIDConverter.class) // 🔥 Convertit UUID <-> String pour SQLite
//     // private UUID id;


//     @Id
//     @Column(columnDefinition = "TEXT", unique = true, nullable = false) // ✅ Stocke UUID sous forme de `String`
//     private String id;

//     @Column(nullable = false)
//     private String numbers;

//     @Column(nullable = false, name = "lucky_number")
//     private int chanceNumber;

//     @Column(nullable = false, name = "draw_date")
//     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//     private LocalDate drawDate;

//     // @Column(nullable = false, name = "draw_day")
//     // private String drawDay;

//     @Column(nullable = false, name = "user_id") // Stocké sous forme de `String` en base
//     private String userId;

//     // @ManyToOne
//     // @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
//     // private User user; // 🔥 Relation correcte avec User

// //     // Getters et Setters
// //     public UUID getId() {
// //         return id;
// //     }

// //     public void setId(UUID id) {
// //         this.id = id;
// //     }

// //     public String getNumbers() {
// //         return numbers;
// //     }

// //     public void setNumbers(String numbers) {
// //         this.numbers = numbers;
// //     }

// //     public int getChanceNumber() {
// //         return chanceNumber;
// //     }

// //     public void setChanceNumber(int chanceNumber) {
// //         this.chanceNumber = chanceNumber;
// //     }

// //     public LocalDate getDrawDate() {
// //         return drawDate;
// //     }

// //     public void setDrawDate(LocalDate drawDate) {
// //         this.drawDate = drawDate;
// //     }

// //     public String getDrawDay() {
// //         return drawDay;
// //     }

// //     public void setDrawDay(String drawDay) {
// //         this.drawDay = drawDay;
// //     }

// //     public UUID getUserId() {
// //         return userId != null ? UUID.fromString(userId) : null;
// //     }

// //     public void setUserId(UUID userId) {
// //         this.userId = userId != null ? userId.toString() : null;
// //     }
// // }

// //     /** ✅ Génère un UUID sous forme de `String` avant insertion en base */
// //     @PrePersist
// //     public void generateUUID() {
// //         if (id == null) {
// //             id = UUID.randomUUID().toString();
// //         }
// //     }

// //     /** ✅ Constructeurs */
// //     public Ticket() {}

// //     public Ticket(String numbers, int chanceNumber, LocalDate drawDate, String drawDay, UUID userId) {
// //         this.id = UUID.randomUUID().toString(); // 🔥 Génération d'un UUID unique sous forme de `String`
// //         setNumbers(numbers);
// //         setChanceNumber(chanceNumber);
// //         this.drawDate = drawDate;
// //         this.drawDay = drawDay;
// //         this.userId = userId != null ? userId.toString() : null; // 🔥 Correction ici
// //     }

// //     /** ✅ Getters & Setters */
// //     public UUID getId() { return id != null ? UUID.fromString(id) : null; }
// //     public void setId(UUID id) { this.id = id != null ? id.toString() : null; }

// //     public String getNumbers() { return numbers; }
// //     public void setNumbers(String numbers) {
// //         if (!isValidNumbers(numbers)) {
// //             throw new IllegalArgumentException("Les numéros doivent être au format '1-2-3-4-5' avec des valeurs entre 1 et 49.");
// //         }
// //         this.numbers = numbers;
// //     }

// //     public int getChanceNumber() { return chanceNumber; }
// //     public void setChanceNumber(int chanceNumber) {
// //         if (chanceNumber < 1 || chanceNumber > 10) {
// //             throw new IllegalArgumentException("Le numéro chance doit être entre 1 et 10.");
// //         }
// //         this.chanceNumber = chanceNumber;
// //     }

// //     public LocalDate getDrawDate() { return drawDate; }
// //     public void setDrawDate(LocalDate drawDate) { this.drawDate = drawDate; }

// //     public String getDrawDay() { return drawDay; }
// //     public void setDrawDay(String drawDay) { this.drawDay = drawDay; }

// //     public UUID getUserId() {
// //         return userId != null ? UUID.fromString(userId) : null;
// //     }

// //     public void setUserId(UUID userId) {
// //         this.userId = userId != null ? userId.toString() : null;
// //     }

// //     /** ✅ Vérifie si la chaîne numbers suit le format "1-2-3-4-5" */
// //     private boolean isValidNumbers(String numbers) {
// //         String regex = "^(\\d{1,2}-){4}\\d{1,2}$"; // Ex: "1-2-3-4-5"
// //         return Pattern.matches(regex, numbers);
// //     }
// // }

//     // ✅ Génère un UUID sous forme de `String` avant insertion en base
//     @PrePersist
//     public void generateUUID() {
//         if (id == null) {
//             id = UUID.randomUUID().toString();
//         }
//     }

//     // 📌 Getters & Setters
//     public String getId() { return id; }
//     public void setId(String id) { this.id = id; }

//     public String getNumbers() { return numbers; }
//     public void setNumbers(String numbers) { this.numbers = numbers; }

//     public int getChanceNumber() { return chanceNumber; }
//     public void setChanceNumber(int chanceNumber) { this.chanceNumber = chanceNumber; }

//     // public LocalDate getDrawDate() { return drawDate; }
//     // public void setDrawDate(LocalDate drawDate) { this.drawDate = drawDate; }

//     // public String getDrawDay() { return drawDay; }
//     // public void setDrawDay(String drawDay) { this.drawDay = drawDay; }

//     public User getUser() { return userId; }  // ✅ Getter pour `User`
//     public void setUser(User user) { this.userId = user; }  // ✅ Setter pour `User`

//     //✅ Ajout de `getUserId()` pour corriger l'erreur dans `TicketController`
//     // public String getUserId() {
//     //     return (user != null) ? user.getId() : null;
//     // }


// }

package com.fdjloto.api.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @Column(columnDefinition = "TEXT", unique = true, nullable = false) // ✅ Stocke l'UUID sous forme de String pour SQLite
    private String id;

    @Column(nullable = false)
    private String numbers; // ✅ Stocke les numéros du ticket (ex: "1-2-3-4-5")

    @Column(nullable = false, name = "lucky_number")
    private int chanceNumber; // ✅ Numéro chance entre 1 et 10

    @Column(nullable = false, name = "draw_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") // ✅ Format JSON pour éviter les erreurs de sérialisation
    private LocalDate drawDate;

    @Column(nullable = false, name = "user_id") // ✅ Stocke l'UUID utilisateur sous forme de String
    private String userId;

    /** ✅ Génère un UUID sous forme de `String` avant insertion en base */
    @PrePersist
    public void generateUUID() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }

    // 📌 Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNumbers() { return numbers; }
    public void setNumbers(String numbers) { this.numbers = numbers; }

    public int getChanceNumber() { return chanceNumber; }
    public void setChanceNumber(int chanceNumber) {
        if (chanceNumber < 1 || chanceNumber > 10) {
            throw new IllegalArgumentException("Le numéro chance doit être entre 1 et 10.");
        }
        this.chanceNumber = chanceNumber;
    }

    public LocalDate getDrawDate() { return drawDate; }
    public void setDrawDate(LocalDate drawDate) { this.drawDate = drawDate; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; } // ✅ Correction de l'erreur dans `TicketController`

}

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


package com.fdjloto.api.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;
import com.fdjloto.api.converter.*;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tickets")  // Assurez-vous que le nom de la table est bien "ticket"
public class Ticket {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String numbers;

    @Column(nullable = false, name = "lucky_number")
    private int chanceNumber;

    // @Column(nullable = false, name = "draw_date")
    // private LocalDate drawDate;

    @Column(nullable = false, name = "draw_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Convert(converter = DateConverter.class)
    private LocalDate drawDate;

    // @Column(nullable = false, name = "draw_date")
    // private String drawDate;


    @Column(nullable = false, name = "draw_day")
    private String drawDay;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    // Getters et Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public int getChanceNumber() {
        return chanceNumber;
    }

    public void setChanceNumber(int chanceNumber) {
        this.chanceNumber = chanceNumber;
    }

    public LocalDate getDrawDate() {
        return drawDate;
    }

    public void setDrawDate(LocalDate drawDate) {
        this.drawDate = drawDate;
    }

    public String getDrawDay() {
        return drawDay;
    }

    public void setDrawDay(String drawDay) {
        this.drawDay = drawDay;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserEmail() {
        return user.getEmail();
    }

}

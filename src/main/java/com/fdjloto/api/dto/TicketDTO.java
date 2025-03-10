// package com.fdjloto.api.dto;

// import com.fdjloto.api.model.Ticket;

// public class TicketDTO {
// 	private String id;
// 	private String numbers;
// 	private int chanceNumber;
// 	private String drawDate;
// 	private String drawDay;
// 	private String userEmail;

// 	public TicketDTO() {
// 	}

// 	// public TicketDTO(String id, String numbers, int chanceNumber, String drawDate, String drawDay, String userEmail) {
// 	// 	this.id = ticket.getId();
// 	// 	this.numbers = ticket.getNumbers();
// 	// 	this.chanceNumber = ticket.getChanceNumber();
// 	// 	this.drawDate = ticket.getDrawDate();
// 	// 	this.drawDay = ticket.getDrawDay();
// 	// 	this.userEmail = ticket.getUser().getEmail(); // 🔥 Récupère l'email sans initialiser l'entité complète
//     // }

// 	public TicketDTO(Ticket ticket) {
//     this.id = ticket.getId();
//     this.numbers = ticket.getNumbers();
//     this.chanceNumber = ticket.getChanceNumber();
//     this.drawDate = ticket.getDrawDate().toString(); // Convert LocalDate en String
//     this.drawDay = ticket.getDrawDay();
//     this.userEmail = ticket.getUser().getEmail();
// }



// 	public String getId() {
// 		return id;
// 	}

// 	public String getNumbers() {
// 		return numbers;
// 	}

// 	public int getChanceNumber() {
// 		return chanceNumber;
// 	}

// 	public String getDrawDate() {
// 		return drawDate;
// 	}

// 	public String getDrawDay() {
// 		return drawDay;
// 	}

// 	public String getUserEmail() {
// 		return userEmail;
// 	}

// }

// package com.fdjloto.api.dto;

// import com.fdjloto.api.model.Ticket;
// import java.time.format.DateTimeFormatter;

// public class TicketDTO {
//     private String id;
//     private String userId;
//     private String numbers;
//     private String chanceNumber;
//     private String createdAt;
// 	private String updateAt;

//     // ✅ Constructeur avec un Ticket
//     public TicketDTO(Ticket ticket) {
//         this.id = ticket.getId();
//         this.userId = ticket.getUser().getId();
//         this.numbers = ticket.getNumbers();
//         this.chanceNumber = String.valueOf(ticket.getChanceNumber()); // ✅ Conversion int -> String

//         // ✅ Vérification si `getCreatedAt()` existe
//         if (ticket.getCreatedAt() != null) {
//             this.createdAt = ticket.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
//         } else {
//             this.createdAt = "N/A"; // 🔥 Valeur par défaut si null
//         }
// 		// ✅ Vérification si `getUpdatedAt()` existe
// 		if (ticket.getUpdatedAt() != null) {
// 			this.updatedAt = ticket.getUpdatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
// 		} else {
// 			this.updatedAt = null; // ⚠️ Laisse `null` pour correspondre au JSON de sortie
// 		}
//     }

//     // ✅ Constructeur par défaut
//     public TicketDTO() {}

//     // ✅ Getters
//     public String getId() { return id; }
//     public String getUserId() { return userId; }
//     public String getNumbers() { return numbers; }
// 	public String getChanceNumber() { return chanceNumber; }
//     public String getCreatedAt() { return createdAt; }
//     public String getUpdatedAt() { return updatedAt; } // ✅ Correction du getter
// }

// package com.fdjloto.api.dto;

// import com.fdjloto.api.model.Ticket;
// import java.time.format.DateTimeFormatter;

// public class TicketDTO {
//     private String id;
//     private String userId;
//     private String numbers;
//     private String chanceNumber;
//     private String drawDate;
//     private String createdAt;
//     private String updatedAt; // ✅ Correction du nom

//     // ✅ Constructeur avec un Ticket
//     public TicketDTO(Ticket ticket) {
//         this.id = ticket.getId();
//         this.userId = ticket.getUser().getId();
//         this.numbers = ticket.getNumbers();
//         this.chanceNumber = String.valueOf(ticket.getChanceNumber()); // ✅ Conversion int -> String

//         // ✅ Vérification si `drawDate` existe
//         if (ticket.getDrawDate() != null) {
//             this.drawDate = ticket.getDrawDate().toString(); // ✅ Conversion LocalDate -> String
//         }

//         // ✅ Vérification si `getCreatedAt()` existe
//         if (ticket.getCreatedAt() != null) {
//             this.createdAt = ticket.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
//         } else {
//             this.createdAt = "N/A"; // 🔥 Valeur par défaut si null
//         }

//         // ✅ Vérification si `getUpdatedAt()` existe
//         if (ticket.getUpdatedAt() != null) {
//             this.updatedAt = ticket.getUpdatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
//         } else {
//             this.updatedAt = null; // ⚠️ Laisse `null` pour correspondre au JSON de sortie
//         }
//     }

//     // ✅ Constructeur par défaut
//     public TicketDTO() {}

//     // ✅ Getters
//     public String getId() { return id; }
//     public String getUserId() { return userId; }
//     public String getNumbers() { return numbers; }
//     public String getChanceNumber() { return chanceNumber; }
//     public String getDrawDate() { return drawDate; } // ✅ Ajout du getter
//     public String getCreatedAt() { return createdAt; }
//     public String getUpdatedAt() { return updatedAt; } // ✅ Getter corrigé
// }

package com.fdjloto.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fdjloto.api.model.Ticket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TicketDTO {
    private String id;
    private String userId;
    private String numbers;
    private String chanceNumber;
    private String drawDate;  // ✅ Ajout de drawDate
    private double gains;
    private String status;
    // private String createdAt;
    // private String updatedAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String updatedAt;

    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    // private LocalDateTime createdAt;

    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    // private LocalDateTime updatedAt;



    // @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    // private LocalDateTime createdAt;

    // @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    // private LocalDateTime updatedAt;


    // ✅ Constructeur avec un Ticket
    public TicketDTO(Ticket ticket) {
        this.id = ticket.getId();
        this.userId = ticket.getUser().getId();
        this.numbers = ticket.getNumbers();
        this.chanceNumber = String.valueOf(ticket.getChanceNumber()); // ✅ Conversion int -> String

        // ✅ Vérification si `drawDate` existe
        if (ticket.getDrawDate() != null) {
            this.drawDate = ticket.getDrawDate().toString(); // ✅ Conversion LocalDate -> String
        }

        // ✅ Vérification si `createdAt` existe
        if (ticket.getCreatedAt() != null) {
            this.createdAt = ticket.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } else {
            this.createdAt = "N/A"; // 🔥 Valeur par défaut si null
        }

        // ✅ Vérification si `updatedAt` existe
        if (ticket.getUpdatedAt() != null) {
            this.updatedAt = ticket.getUpdatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } else {
            this.updatedAt = null; // ⚠️ Laisse `null` pour correspondre au JSON de sortie
        }
    }

    // ✅ Constructeur par défaut
    public TicketDTO() {}

    // ✅ Getters
    public String getId() { return id; }
    public String getUserId() { return userId; }
    public String getNumbers() { return numbers; }
    public String getChanceNumber() { return chanceNumber; }
    public String getDrawDate() { return drawDate; } // ✅ Ajout du getter
    public String getCreatedAt() { return createdAt; }
    public String getUpdatedAt() { return updatedAt; }
    public double getGains() { // ✅ Ajout
        return gains;
    }

    public String getStatus() { // ✅ Ajout
        return status;
    }

    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    // private LocalDateTime drawDate;

}

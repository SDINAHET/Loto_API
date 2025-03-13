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
    // private String createdAt;
    // private String updatedAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String updatedAt;

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

}

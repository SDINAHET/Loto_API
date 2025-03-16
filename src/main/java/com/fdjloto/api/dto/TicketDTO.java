package com.fdjloto.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fdjloto.api.model.Ticket;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * DTO (Data Transfer Object) for representing a lottery ticket.
 * This class is used to transfer formatted ticket data between the backend and API consumers.
 */
@Schema(description = "Represents a lottery ticket with numbers, draw date, and timestamps.")
public class TicketDTO {

    @Schema(description = "Unique ticket identifier", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;

    @Schema(description = "ID of the user who owns the ticket", example = "789e1234-e89b-56d3-a456-426614174321")
    private String userId;

    @Schema(description = "Numbers selected on the ticket ( - separated)", example = "5-12-23-34-45")
    private String numbers;

    @Schema(description = "The lucky number (Numéro Chance)", example = "7")
    private String chanceNumber;

    @Schema(description = "The draw date of the ticket in 'yyyy-MM-dd' format", example = "2025-03-15")
    private String drawDate;

    @Schema(description = "The timestamp when the ticket was created in 'yyyy-MM-dd HH:mm:ss' format",
            example = "2025-03-15 12:30:45")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String createdAt;

    @Schema(description = "The timestamp when the ticket was last updated in 'yyyy-MM-dd HH:mm:ss' format",
            example = "2025-03-16 15:45:30")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String updatedAt;

    /**
     * Constructor that initializes a TicketDTO from a Ticket entity.
     *
     * @param ticket The Ticket entity used to populate this DTO.
     */
    public TicketDTO(Ticket ticket) {
        this.id = ticket.getId();
        this.userId = ticket.getUser().getId();
        this.numbers = ticket.getNumbers();
        this.chanceNumber = String.valueOf(ticket.getChanceNumber()); // ✅ Convert int to String

        // ✅ Check if `drawDate` exists before converting
        if (ticket.getDrawDate() != null) {
            this.drawDate = ticket.getDrawDate().toString(); // ✅ Convert LocalDate to String
        }

        // ✅ Check if `createdAt` exists before converting
        if (ticket.getCreatedAt() != null) {
            this.createdAt = ticket.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } else {
            this.createdAt = "N/A"; // 🔥 Default value if null
        }

        // ✅ Check if `updatedAt` exists before converting
        if (ticket.getUpdatedAt() != null) {
            this.updatedAt = ticket.getUpdatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } else {
            this.updatedAt = null; // ⚠️ Keep `null` to match the expected JSON output
        }
    }

    /**
     * Default constructor (needed for frameworks like Jackson).
     */
    public TicketDTO() {}

    // ✅ Getters

    /**
     * Gets the ticket ID.
     * @return The unique ID of the ticket.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the user ID associated with the ticket.
     * @return The user ID.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Gets the selected numbers on the ticket.
     * @return The numbers as a string.
     */
    public String getNumbers() {
        return numbers;
    }

    /**
     * Gets the lucky number (Numéro Chance).
     * @return The lucky number as a string.
     */
    public String getChanceNumber() {
        return chanceNumber;
    }

    /**
     * Gets the draw date of the ticket.
     * @return The draw date as a formatted string.
     */
    public String getDrawDate() {
        return drawDate;
    }

    /**
     * Gets the creation timestamp of the ticket.
     * @return The createdAt timestamp formatted as "yyyy-MM-dd HH:mm:ss".
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Gets the last updated timestamp of the ticket.
     * @return The updatedAt timestamp formatted as "yyyy-MM-dd HH:mm:ss".
     */
    public String getUpdatedAt() {
        return updatedAt;
    }
}

/**
 * 💡 Explanation:
 * - `TicketDTO` is a Data Transfer Object (DTO) designed for API responses.
 * - The `@JsonFormat` annotation ensures the `createdAt` and `updatedAt` fields follow the "yyyy-MM-dd HH:mm:ss" format.
 * - This DTO is constructed from a `Ticket` entity to provide a structured representation of ticket data.
 * - **Null checks** ensure proper handling of missing values.
 */

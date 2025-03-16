package com.fdjloto.api.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fdjloto.api.converter.LocalDateTimeAttributeConverter;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents a lottery ticket entity stored in the database.
 * This model maps to the "tickets" table.
 */
@Schema(description = "Represents a lottery ticket entity stored in the database.")
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @Column(name = "id", columnDefinition = "TEXT") // SQLite does not support UUID directly
    @Schema(description = "Unique identifier of the ticket", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;

    @Column(nullable = false)
    @Schema(description = "Numbers selected on the ticket (comma-separated)", example = "5,12,23,34,45")
    private String numbers; // Lottery numbers chosen by the user

    @Column(nullable = false, name = "lucky_number")
    @Schema(description = "The lucky number (Numéro Chance)", example = "7")
    private int chanceNumber; // The "lucky number" or "bonus ball"

    @Column(nullable = false, name = "draw_date")
    @Schema(description = "The draw date of the ticket", example = "2025-03-15")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate drawDate; // Date of the lottery draw

    @Column(nullable = true, name = "draw_day")
    @Schema(description = "The day of the week when the draw takes place", example = "Wednesday")
    private String drawDay; // Name of the draw day (e.g., "Wednesday", "Saturday")

    // Timestamp when the ticket was created
    @Column(name = "created_at")
    @Schema(description = "Timestamp when the ticket was created", example = "2025-03-15 12:30:45")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime createdAt;

    // Timestamp when the ticket was last updated
    @Column(name = "updated_at")
    @Schema(description = "Timestamp when the ticket was last updated", example = "2025-03-16 15:45:30")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime updatedAt;

    /**
     * Many-to-One relationship with the User entity.
     * Each ticket is associated with a single user.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore // Prevents exposing the user object in JSON responses
    @Schema(description = "The user who owns the ticket (hidden in JSON responses)")
    private User user;

    /**
     * Default constructor:
     * - Generates a unique UUID as the ticket ID.
     * - Initializes the createdAt timestamp to the current date and time.
     */
    public Ticket() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
    }

    /**
     * PrePersist hook:
     * - Automatically sets createdAt and updatedAt timestamps before saving a new entity.
     */
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * PreUpdate hook:
     * - Automatically updates the updatedAt timestamp before modifying an entity.
     */
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // ✅ Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id.toString();
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
        return user.getEmail(); // Returns the email of the associated user
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = LocalDateTime.parse(createdAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = LocalDateTime.parse(updatedAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

/**
 * 💡 Explanation:
 * - **Ticket** is an entity mapped to the `tickets` table in the database.
 * - Uses **UUID** as the primary key since SQLite does not support UUID natively.
 * - Includes **lottery numbers, lucky number, draw date, timestamps**.
 * - Uses **@PrePersist and @PreUpdate** to automatically manage timestamps.
 * - Establishes a **Many-to-One** relationship with the **User** entity.
 * - Uses **@JsonIgnore** to prevent serialization of the **User** object.
 */

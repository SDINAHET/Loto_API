package com.fdjloto.api.model;

import jakarta.persistence.*;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * Represents a lottery ticket gain entity.
 * This model stores information about winnings associated with a ticket.
 */
@Entity
@Table(name = "ticket_gains")
public class TicketGain {

    @Id
    @Schema(description = "Unique identifier for the ticket gain entry", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id; // Unique identifier for the ticket gain entry

    /**
     * One-to-One relationship with the Ticket entity.
     * Each gain record is linked to a single ticket.
     */
    @OneToOne
    @JoinColumn(name = "ticket_id", nullable = false) // Foreign key linking to the Ticket table
    @Schema(description = "The associated ticket for this gain entry")
    private Ticket ticket;

    @Schema(description = "Number of matching numbers in the draw", example = "3")
    private int matchingNumbers; // Number of matching numbers in the draw

    @Schema(description = "Indicates whether the lucky number (Numéro Chance) matched", example = "true")
    private boolean luckyNumberMatch; // Indicates whether the lucky number was matched

    @Schema(description = "The calculated amount of winnings", example = "25.50")
    private double gainAmount; // The amount of money won

    /**
     * Default constructor required by JPA.
     */
    public TicketGain() {}

    /**
     * Parameterized constructor to initialize all fields.
     *
     * @param id Unique identifier for the gain entry
     * @param ticket Associated ticket
     * @param matchingNumbers Number of matching numbers
     * @param luckyNumberMatch Whether the lucky number matches
     * @param gainAmount The amount of money won
     */
    public TicketGain(String id, Ticket ticket, int matchingNumbers, boolean luckyNumberMatch, double gainAmount) {
        this.id = id;
        this.ticket = ticket;
        this.matchingNumbers = matchingNumbers;
        this.luckyNumberMatch = luckyNumberMatch;
        this.gainAmount = gainAmount;
    }

    // ✅ Getters

    public String getId() {
        return id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public int getMatchingNumbers() {
        return matchingNumbers;
    }

    public boolean isLuckyNumberMatch() {
        return luckyNumberMatch;
    }

    public double getGainAmount() {
        return gainAmount;
    }

    // ✅ Setters (added for data modification)

    public void setMatchingNumbers(int matchingNumbers) {
        this.matchingNumbers = matchingNumbers;
    }

    public void setLuckyNumberMatch(boolean luckyNumberMatch) {
        this.luckyNumberMatch = luckyNumberMatch;
    }

    public void setGainAmount(double gainAmount) {
        this.gainAmount = gainAmount;
    }
}

/**
 * 💡 Explanation:
 * - **TicketGain** represents the earnings from a lottery ticket.
 * - It has a **One-to-One relationship** with the `Ticket` entity.
 * - Stores **matching numbers, lucky number status, and winnings**.
 * - Uses a **default constructor** for JPA and a **parameterized constructor** for initialization.
 * - Includes **getters and setters** to allow reading and modifying data.
 */

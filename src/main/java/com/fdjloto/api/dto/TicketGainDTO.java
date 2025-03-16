package com.fdjloto.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fdjloto.api.model.TicketGain;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO (Data Transfer Object) for representing ticket gains.
 * This class is used to transfer structured gain data between the backend and API consumers.
 */
@JsonIgnoreProperties(ignoreUnknown = true) // Ignores unknown JSON properties during deserialization
@Schema(description = "Represents the winnings associated with a lottery ticket.")
public class TicketGainDTO {

    @Schema(description = "Unique identifier of the ticket", example = "123e4567-e89b-12d3-a456-426614174000")
    private String ticketId;

    @Schema(description = "Number of matching numbers in the draw", example = "3")
    private int matchingNumbers;

    @Schema(description = "Indicates whether the lucky number (Numéro Chance) matched", example = "true")
    private boolean luckyNumberMatch;

    @Schema(description = "The calculated amount of winnings", example = "25.50")
    private double gainAmount;

    /**
     * Constructor to initialize a TicketGainDTO with explicit parameters.
     *
     * @param ticketId         The unique ID of the ticket.
     * @param matchingNumbers  The number of matching numbers in the draw.
     * @param luckyNumberMatch Whether the lucky number matched.
     * @param gainAmount       The calculated gain amount.
     */
    public TicketGainDTO(String ticketId, int matchingNumbers, boolean luckyNumberMatch, double gainAmount) {
        this.ticketId = ticketId;
        this.matchingNumbers = matchingNumbers;
        this.luckyNumberMatch = luckyNumberMatch;
        this.gainAmount = gainAmount;
    }

    /**
     * Constructor to initialize a TicketGainDTO from a TicketGain entity.
     *
     * @param ticketGain The TicketGain entity used to populate this DTO.
     */
    public TicketGainDTO(TicketGain ticketGain) {
        this.ticketId = ticketGain.getTicket().getId();
        this.matchingNumbers = ticketGain.getMatchingNumbers();
        this.luckyNumberMatch = ticketGain.isLuckyNumberMatch();
        this.gainAmount = ticketGain.getGainAmount();
    }

    // ✅ Getters

    /**
     * Gets the ticket ID.
     * @return The unique ID of the ticket.
     */
    public String getTicketId() {
        return ticketId;
    }

    /**
     * Gets the number of matching numbers in the draw.
     * @return The count of matching numbers.
     */
    public int getMatchingNumbers() {
        return matchingNumbers;
    }

    /**
     * Checks whether the lucky number (Numéro Chance) matched.
     * @return True if the lucky number matched, otherwise false.
     */
    public boolean isLuckyNumberMatch() {
        return luckyNumberMatch;
    }

    /**
     * Gets the amount of winnings.
     * @return The calculated gain amount.
     */
    public double getGainAmount() {
        return gainAmount;
    }
}

/**
 * 💡 Explanation:
 * - `TicketGainDTO` is a **Data Transfer Object (DTO)** designed to represent ticket winnings in API responses.
 * - **`@JsonIgnoreProperties(ignoreUnknown = true)`** ensures that unknown JSON fields do not cause errors during deserialization.
 * - **Two constructors:**
 *   - One accepts explicit parameters.
 *   - One initializes from a `TicketGain` entity.
 * - The class provides **getter methods** to expose data in a structured way.
 */

package com.fdjloto.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO (Data Transfer Object) for representing lottery gain results.
 * This class is used to transfer information about the winnings of a ticket.
 */
@Schema(description = "Represents the winnings of a ticket, including the matching numbers, lucky number match, and total gain amount.")
public class GainResultDTO {

    @Schema(description = "The unique identifier of the ticket", example = "123e4567-e89b-12d3-a456-556642440000")
    private String ticketId;

    @Schema(description = "The number of matching numbers on the ticket", example = "4")
    private int matchingNumbers;

    @Schema(description = "Indicates whether the lucky number matches", example = "true")
    private boolean luckyNumberMatch;

    @Schema(description = "The total amount won", example = "100.50")
    private double gainAmount;

    /**
     * Constructor to initialize a gain result.
     *
     * @param ticketId         The ticket's unique identifier.
     * @param matchingNumbers  The number of matching numbers.
     * @param luckyNumberMatch Whether the lucky number matches.
     * @param gainAmount       The amount of money won.
     */
    public GainResultDTO(String ticketId, int matchingNumbers, boolean luckyNumberMatch, double gainAmount) {
        this.ticketId = ticketId;
        this.matchingNumbers = matchingNumbers;
        this.luckyNumberMatch = luckyNumberMatch;
        this.gainAmount = gainAmount;
    }

    /**
     * Gets the ticket ID.
     *
     * @return The unique ticket identifier.
     */
    public String getTicketId() {
        return ticketId;
    }

    /**
     * Gets the number of matching numbers.
     *
     * @return The count of matching numbers.
     */
    public int getMatchingNumbers() {
        return matchingNumbers;
    }

    /**
     * Checks if the lucky number matches.
     *
     * @return `true` if the lucky number matches, otherwise `false`.
     */
    public boolean isLuckyNumberMatch() {
        return luckyNumberMatch;
    }

    /**
     * Gets the total gain amount.
     *
     * @return The amount of money won.
     */
    public double getGainAmount() {
        return gainAmount;
    }
}

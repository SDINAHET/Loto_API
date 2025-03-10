package com.fdjloto.api.dto;

public class GainResultDTO {
    private String ticketId;
    private int matchingNumbers;
    private boolean luckyNumberMatch;
    private double gainAmount;

    public GainResultDTO(String ticketId, int matchingNumbers, boolean luckyNumberMatch, double gainAmount) {
        this.ticketId = ticketId;
        this.matchingNumbers = matchingNumbers;
        this.luckyNumberMatch = luckyNumberMatch;
        this.gainAmount = gainAmount;
    }

    public String getTicketId() {
        return ticketId;
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
}

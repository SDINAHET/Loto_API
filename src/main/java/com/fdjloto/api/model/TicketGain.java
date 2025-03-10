package com.fdjloto.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ticket_gains")
public class TicketGain {

    @Id
    private String id;

    @OneToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    private int matchingNumbers;
    private boolean luckyNumberMatch;
    private double gainAmount;

    public TicketGain() {}

    public TicketGain(String id, Ticket ticket, int matchingNumbers, boolean luckyNumberMatch, double gainAmount) {
        this.id = id;
        this.ticket = ticket;
        this.matchingNumbers = matchingNumbers;
        this.luckyNumberMatch = luckyNumberMatch;
        this.gainAmount = gainAmount;
    }

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

    // ✅ Ajout des setters pour éviter l'erreur
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

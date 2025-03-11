// package com.fdjloto.api.dto;

// import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
// import com.fdjloto.api.model.TicketGain;

// @JsonIgnoreProperties(ignoreUnknown = true)
// public class TicketGainDTO {
//     private String ticketId;
//     private int matchingNumbers;
//     private boolean luckyNumberMatch;
//     private double gainAmount;

//     public TicketGainDTO(TicketGain ticketGain) {
//         this.ticketId = ticketGain.getTicket().getId();
//         this.matchingNumbers = ticketGain.getMatchingNumbers();
//         this.luckyNumberMatch = ticketGain.isLuckyNumberMatch();
//         this.gainAmount = ticketGain.getGainAmount();
//     }

//     public String getTicketId() { return ticketId; }
//     public int getMatchingNumbers() { return matchingNumbers; }
//     public boolean isLuckyNumberMatch() { return luckyNumberMatch; }
//     public double getGainAmount() { return gainAmount; }
// }

package com.fdjloto.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fdjloto.api.model.TicketGain;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketGainDTO {
    private String ticketId;
    private int matchingNumbers;
    private boolean luckyNumberMatch;
    private double gainAmount;

    public TicketGainDTO(String ticketId, int matchingNumbers, boolean luckyNumberMatch, double gainAmount) {
        this.ticketId = ticketId;
        this.matchingNumbers = matchingNumbers;
        this.luckyNumberMatch = luckyNumberMatch;
        this.gainAmount = gainAmount;
    }

    public TicketGainDTO(TicketGain ticketGain) {
        this.ticketId = ticketGain.getTicket().getId();
        this.matchingNumbers = ticketGain.getMatchingNumbers();
        this.luckyNumberMatch = ticketGain.isLuckyNumberMatch();
        this.gainAmount = ticketGain.getGainAmount();
    }

    public String getTicketId() { return ticketId; }
    public int getMatchingNumbers() { return matchingNumbers; }
    public boolean isLuckyNumberMatch() { return luckyNumberMatch; }
    public double getGainAmount() { return gainAmount; }
}

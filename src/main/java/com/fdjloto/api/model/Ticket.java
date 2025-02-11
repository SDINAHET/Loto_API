package com.fdjloto.api.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Ticket {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private UUID userId; // Modification ici, assure-toi que c'est bien UUID et non String

    @Column(nullable = false)
    private String numbers;

    @Column(nullable = false)
    private int chanceNumber;

    @Column(nullable = false)
    private String drawDate;

    // public Ticket() {
    //     // this.id = UUID.randomUUID();
    // }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
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

    public String getDrawDate() {
        return drawDate;
    }

    public void setDrawDate(String drawDate) {
        this.drawDate = drawDate;
    }
}


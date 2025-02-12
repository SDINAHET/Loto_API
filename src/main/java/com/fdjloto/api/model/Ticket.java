package com.fdjloto.api.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;
import java.util.regex.Pattern;

@Entity
public class Ticket {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private String numbers; // Format: "1-2-3-4-5"

    @Column(nullable = false)
    private int chanceNumber = 1; // Par défaut 1, entre 1 et 10

    @Column(nullable = false)
    private LocalDate drawDate;

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
        if (!isValidNumbers(numbers)) {
            throw new IllegalArgumentException("Les numéros doivent être au format '1-2-3-4-5' avec des valeurs entre 1 et 49.");
        }
        this.numbers = numbers;
    }

    public int getChanceNumber() {
        return chanceNumber;
    }

    public void setChanceNumber(int chanceNumber) {
        if (chanceNumber <= 1 || chanceNumber > 10) {
            throw new IllegalArgumentException("Le numéro chance doit être entre 1 et 10.");
        }
        this.chanceNumber = chanceNumber;
    }

    public LocalDate getDrawDate() {
        return drawDate;
    }

    public void setDrawDate(LocalDate drawDate) {
        this.drawDate = drawDate;
    }

    private boolean isValidNumbers(String numbers) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        return Pattern.matches(regex, numbers);
    }
}


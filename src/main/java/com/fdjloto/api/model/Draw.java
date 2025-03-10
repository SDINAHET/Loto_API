package com.fdjloto.api.model;

import java.util.List;

public class Draw {
    private String id;
    private List<Integer> numbers;
    private int chanceNumber;
    private String drawDate;

    // 🔥 Constructeur
    public Draw(String id, List<Integer> numbers, int chanceNumber, String drawDate) {
        this.id = id;
        this.numbers = numbers;
        this.chanceNumber = chanceNumber;
        this.drawDate = drawDate;
    }

    // 🔥 Getters et Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
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

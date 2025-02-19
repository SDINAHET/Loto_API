package com.fdjloto.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.Map;

@Document(collection = "predictions")
public class PredictionTirageModel {

    @Id
    private String id;
    private List<Integer> probableNumbers;
    private int probableChance;
    private Map<Integer, Double> stdDevNumbers;
    private double stdDevChance;
    private Map<Integer, String> confidenceIntervals; // ✅ Ajouter ce champ
    private String confidenceChance; // ✅ Ajouter ce champ
    private Map<Integer, Double> sortieRates; // ✅ Ajout du taux de sortie

    // ✅ Getters & Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Integer> getProbableNumbers() {
        return probableNumbers;
    }

    public void setProbableNumbers(List<Integer> probableNumbers) {
        this.probableNumbers = probableNumbers;
    }

    public int getProbableChance() {
        return probableChance;
    }

    public void setProbableChance(int probableChance) {
        this.probableChance = probableChance;
    }

    public Map<Integer, Double> getStdDevNumbers() {
        return stdDevNumbers;
    }

    public void setStdDevNumbers(Map<Integer, Double> stdDevNumbers) {
        this.stdDevNumbers = stdDevNumbers;
    }

    public double getStdDevChance() {
        return stdDevChance;
    }

    public void setStdDevChance(double stdDevChance) {
        this.stdDevChance = stdDevChance;
    }

    public Map<Integer, String> getConfidenceIntervals() {
        return confidenceIntervals;
    }

    public void setConfidenceIntervals(Map<Integer, String> confidenceIntervals) {
        this.confidenceIntervals = confidenceIntervals;
    }

    public String getConfidenceChance() {
        return confidenceChance;
    }

    public void setConfidenceChance(String confidenceChance) {
        this.confidenceChance = confidenceChance;
    }

    // ✅ Ajout des getters et setters pour sortieRates
    public Map<Integer, Double> getSortieRates() {
        return sortieRates;
    }

    public void setSortieRates(Map<Integer, Double> sortieRates) {
        this.sortieRates = sortieRates;
    }
}


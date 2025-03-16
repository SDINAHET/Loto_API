package com.fdjloto.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.Map;

/**
 * Represents a lottery draw prediction stored in a MongoDB collection.
 */
@Schema(description = "Represents a lottery draw prediction stored in MongoDB.")
@Document(collection = "predictions") // Specifies the MongoDB collection where predictions are stored
public class PredictionTirageModel {

    @Id
    @Schema(description = "Unique identifier for the prediction entry", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id; // Unique identifier for the prediction entry

    @Schema(description = "List of predicted lottery numbers", example = "[12, 25, 37, 42, 49]")
    private List<Integer> probableNumbers; // List of predicted lottery numbers

    @Schema(description = "The predicted lucky number (Numéro Chance)", example = "7")
    private int probableChance; // Predicted lucky number (bonus ball)

    @Schema(description = "Standard deviation for each number, representing variation")
    private Map<Integer, Double> stdDevNumbers; // Standard deviation for each number, representing variation

    @Schema(description = "Standard deviation for the lucky number")
    private double stdDevChance; // Standard deviation for the lucky number

    @Schema(description = "Confidence intervals for each number, indicating probability range")
    private Map<Integer, String> confidenceIntervals; // Confidence intervals for each number, indicating probability range

    @Schema(description = "Confidence interval for the lucky number")
    private String confidenceChance; // Confidence interval for the lucky number

    @Schema(description = "Frequency rates of number appearances in past draws")
    private Map<Integer, Double> sortieRates; // Frequency rates of number appearances in past draws

    /**
     * ✅ Getters & Setters for accessing and modifying class attributes.
     */

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

    public Map<Integer, Double> getSortieRates() {
        return sortieRates;
    }

    public void setSortieRates(Map<Integer, Double> sortieRates) {
        this.sortieRates = sortieRates;
    }
}

/**
 * 💡 Explanation:
 * - **PredictionTirageModel** represents a **predicted draw result** stored in MongoDB.
 * - The model includes:
 *   - **Predicted numbers and lucky number**.
 *   - **Standard deviations**, showing the variation of numbers.
 *   - **Confidence intervals**, providing probability ranges.
 *   - **Sortie rates**, indicating frequency of past appearances.
 * - Uses **@Document** to map it to the `predictions` collection in MongoDB.
 * - Includes **getter and setter methods** to access and modify attributes.
 */

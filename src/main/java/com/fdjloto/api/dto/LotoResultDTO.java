package com.fdjloto.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;

/**
 * DTO (Data Transfer Object) for representing lottery results.
 * This class is used to transfer formatted lottery draw results to the API response.
 */
@Schema(description = "Represents a formatted lottery draw result with numbers and lucky number.")
public class LotoResultDTO {

    @Schema(description = "Unique identifier for the draw result", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;

    @Schema(description = "The draw date formatted as 'dd/MM/yyyy'", example = "15/03/2025")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "Europe/Paris")
    private Date dateDeTirage;

    @Schema(description = "The first drawn number", example = "12")
    private int boule1;

    @Schema(description = "The second drawn number", example = "25")
    private int boule2;

    @Schema(description = "The third drawn number", example = "37")
    private int boule3;

    @Schema(description = "The fourth drawn number", example = "42")
    private int boule4;

    @Schema(description = "The fifth drawn number", example = "49")
    private int boule5;

    @Schema(description = "The lucky number (Numéro Chance)", example = "7")
    private int numeroChance;

    /**
     * Constructor to initialize a LotoResultDTO object.
     *
     * @param id             The unique identifier of the draw.
     * @param dateDeTirage   The draw date.
     * @param boule1         The first drawn number.
     * @param boule2         The second drawn number.
     * @param boule3         The third drawn number.
     * @param boule4         The fourth drawn number.
     * @param boule5         The fifth drawn number.
     * @param numeroChance   The lucky number (Numéro Chance).
     */
    public LotoResultDTO(String id, Date dateDeTirage, int boule1, int boule2, int boule3, int boule4, int boule5, int numeroChance) {
        this.id = id;
        this.dateDeTirage = dateDeTirage;
        this.boule1 = boule1;
        this.boule2 = boule2;
        this.boule3 = boule3;
        this.boule4 = boule4;
        this.boule5 = boule5;
        this.numeroChance = numeroChance;
    }

    // Getters for accessing the attributes.

    /**
     * Gets the unique ID of the draw result.
     *
     * @return The ID of the draw.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the draw date.
     *
     * @return The draw date formatted as "dd/MM/yyyy".
     */
    public Date getDateDeTirage() {
        return dateDeTirage;
    }

    /**
     * Gets the first drawn number.
     *
     * @return The first number.
     */
    public int getBoule1() {
        return boule1;
    }

    /**
     * Gets the second drawn number.
     *
     * @return The second number.
     */
    public int getBoule2() {
        return boule2;
    }

    /**
     * Gets the third drawn number.
     *
     * @return The third number.
     */
    public int getBoule3() {
        return boule3;
    }

    /**
     * Gets the fourth drawn number.
     *
     * @return The fourth number.
     */
    public int getBoule4() {
        return boule4;
    }

    /**
     * Gets the fifth drawn number.
     *
     * @return The fifth number.
     */
    public int getBoule5() {
        return boule5;
    }

    /**
     * Gets the lucky number (Numéro Chance).
     *
     * @return The lucky number.
     */
    public int getNumeroChance() {
        return numeroChance;
    }
}

/**
 * 💡 Explanation:
 * - `LotoResultDTO` is a Data Transfer Object (DTO) used for API responses.
 * - The `@JsonFormat` annotation ensures that the draw date is formatted as "dd/MM/yyyy".
 * - The combination of drawn numbers and the lucky number are included as separate fields.
 */

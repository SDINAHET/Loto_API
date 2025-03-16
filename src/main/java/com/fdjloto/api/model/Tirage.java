package com.fdjloto.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents a lottery draw (Tirage) stored in MongoDB.
 * This model contains information about a specific draw, including the drawn numbers and date.
 */
@Document(collection = "historique") // Ensures this class is mapped to the "historique" collection in MongoDB
@Data // Lombok annotation that automatically generates Getters, Setters, toString(), equals(), and hashCode()
@Schema(description = "Represents a lottery draw stored in MongoDB.")
public class Tirage {

    @Id
    @Schema(description = "Unique identifier for the draw (MongoDB document ID)", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id; // Unique identifier for the draw (MongoDB document ID)

    @Schema(description = "The day of the draw (e.g., 'Monday')", example = "Monday")
    private String jourDeTirage; // The day of the draw (e.g., "Monday")

    @Schema(description = "The actual draw date in string format (YYYY-MM-DD)", example = "2025-03-15")
    private String dateDeTirage; // The actual draw date in string format (YYYY-MM-DD)

    @Schema(description = "The first drawn number", example = "12")
    private int boule1; // First drawn number

    @Schema(description = "The second drawn number", example = "25")
    private int boule2; // Second drawn number

    @Schema(description = "The third drawn number", example = "37")
    private int boule3; // Third drawn number

    @Schema(description = "The fourth drawn number", example = "42")
    private int boule4; // Fourth drawn number

    @Schema(description = "The fifth drawn number", example = "49")
    private int boule5; // Fifth drawn number

    @Schema(description = "The lucky number (Numéro Chance)", example = "7")
    private int numeroChance; // Lucky number (bonus ball)

    /**
     * Returns a list containing the five main drawn numbers.
     *
     * @return List of drawn numbers excluding the lucky number
     */
    public List<Integer> getBoules() {
        return List.of(boule1, boule2, boule3, boule4, boule5);
    }
}

/**
 * 💡 Explanation:
 * - **Tirage** represents a single lottery draw and is stored in MongoDB.
 * - The `@Document(collection = "historique")` annotation maps this class to the **historique** collection.
 * - **Lombok's `@Data` annotation** automatically generates getters, setters, and utility methods.
 * - The `getBoules()` method returns a **list of main numbers** excluding the lucky number.
 * - The `id` field serves as the **primary key** for MongoDB.
 */

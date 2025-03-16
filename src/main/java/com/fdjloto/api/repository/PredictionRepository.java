package com.fdjloto.api.repository;

import com.fdjloto.api.model.PredictionTirageModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing **lottery draw predictions** in MongoDB.
 * This interface extends **MongoRepository**, providing built-in CRUD operations.
 */
@Repository
public interface PredictionRepository extends MongoRepository<PredictionTirageModel, String> {

    /**
     * Retrieves the **latest prediction** based on the most recent entry in the database.
     *
     * @return The most **recently stored** `PredictionTirageModel`.
     *         If no prediction exists, returns **null**.
     */
    PredictionTirageModel findTopByOrderByIdDesc();
}

/**
 * 💡 Explanation:
 * - `PredictionRepository` is a **MongoDB repository** responsible for storing and retrieving lottery predictions.
 * - **`findTopByOrderByIdDesc()`** fetches **the latest prediction** by ordering results **by ID in descending order**.
 * - Uses **Spring Data MongoDB query derivation**, meaning **no need for manual queries**.
 */

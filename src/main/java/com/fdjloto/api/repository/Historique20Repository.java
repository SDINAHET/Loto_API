package com.fdjloto.api.repository;

import com.fdjloto.api.model.Historique20Result;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository interface for accessing **historical lottery results** stored in MongoDB.
 * This interface extends **MongoRepository**, providing built-in CRUD operations.
 */
@Repository
public interface Historique20Repository extends MongoRepository<Historique20Result, String> {

    /**
     * Retrieves the **latest 6 lottery results**, ordered by draw date in descending order.
     *
     * @return A **List** of the most recent 6 `Historique20Result` objects.
     */
    List<Historique20Result> findTop6ByOrderByDateDeTirageDesc();
}

/**
 * 💡 Explanation:
 * - `Historique20Repository` is a **MongoDB repository** that handles historical lottery results.
 * - **`findTop6ByOrderByDateDeTirageDesc()`** retrieves the **6 most recent draws**, sorted by date.
 * - Extends **MongoRepository**, allowing CRUD operations without needing custom implementations.
 * - Uses **automatic query generation** based on Spring Data naming conventions.
 */

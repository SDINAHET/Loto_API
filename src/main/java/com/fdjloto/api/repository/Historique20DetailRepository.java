package com.fdjloto.api.repository;

import com.fdjloto.api.model.Historique20Detail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Date;
import java.util.List;

/**
 * Repository interface for managing **historical lottery draw details** in MongoDB.
 * This interface extends **MongoRepository**, providing built-in CRUD operations.
 */
@Repository
public interface Historique20DetailRepository extends MongoRepository<Historique20Detail, String> {

    /**
     * Finds a **lottery draw detail** by its draw date.
     *
     * @param dateDeTirage The date of the draw.
     * @return An **Optional** containing the draw details if found, otherwise empty.
     */
    Optional<Historique20Detail> findByDateDeTirage(Date dateDeTirage);

    /**
     * Retrieves a list of historical draw details within a specified date range.
     *
     * @param startDate The start date of the search range.
     * @param endDate   The end date of the search range.
     * @return A **List** of `Historique20Detail` objects within the specified range.
     */
    List<Historique20Detail> findByDateDeTirageBetween(Date startDate, Date endDate);
}

/**
 * 💡 Explanation:
 * - This repository handles **historical lottery results** stored in a MongoDB collection.
 * - `findByDateDeTirage()` allows **retrieval of a single draw** by date.
 * - `findByDateDeTirageBetween()` retrieves **multiple draws within a date range**.
 * - The use of **Optional** prevents `null` values and improves handling of missing data.
 */

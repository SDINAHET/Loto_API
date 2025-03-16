package com.fdjloto.api.repository;

import com.fdjloto.api.model.LotoResult;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for accessing **lottery results** stored in MongoDB.
 * This interface extends **MongoRepository**, providing built-in CRUD operations.
 */
public interface LotoRepository extends MongoRepository<LotoResult, String> {

    /**
     * Retrieves all lottery results that fall within a **specific date range**.
     *
     * @param startDate The **start date** of the period.
     * @param endDate   The **end date** of the period.
     * @return A **List** of `LotoResult` objects within the given date range.
     */
    List<LotoResult> findByDateDeTirageBetween(Date startDate, Date endDate);

    /**
     * Retrieves a **single lottery result** for a **specific draw date**.
     *
     * @param dateDeTirage The **draw date** to search for.
     * @return An **Optional** containing the `LotoResult` if found, or empty if not found.
     */
    Optional<LotoResult> findByDateDeTirage(LocalDate dateDeTirage);
}

/**
 * 💡 Explanation:
 * - `LotoRepository` is a **MongoDB repository** that handles lottery results.
 * - **`findByDateDeTirageBetween(startDate, endDate)`** retrieves **all draws** within a given date range.
 * - **`findByDateDeTirage(dateDeTirage)`** fetches a **single draw result** for a specific date.
 * - Uses **Spring Data MongoDB query derivation**, meaning **no need for manual queries**.
 */

package com.fdjloto.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.Map;
import java.util.Optional;
import com.fdjloto.api.model.History;


public interface HistoryRepository extends MongoRepository<History, String> {

    /**
     * 🔍 Recherche les gains du tirage correspondant à la date donnée.
     */
    @Query(value = "{ 'draw_date' : ?0 }", fields = "{ 'gains' : 1 }")
    Optional<Map<Integer, Double>> findGainsByDrawDate(String drawDate);
}


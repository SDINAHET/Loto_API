package com.fdjloto.api.repository;

import com.fdjloto.api.model.PredictionTirageModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredictionRepository extends MongoRepository<PredictionTirageModel, String> {
    PredictionTirageModel findTopByOrderByIdDesc();
}

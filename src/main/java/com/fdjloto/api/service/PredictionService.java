package com.fdjloto.api.service;

import com.fdjloto.api.model.PredictionTirageModel;
import com.fdjloto.api.repository.PredictionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PredictionService {

    private final PredictionRepository predictionRepository;

    @Autowired
    public PredictionService(PredictionRepository predictionRepository) {
        this.predictionRepository = predictionRepository;
    }

    public PredictionTirageModel getLatestPrediction() {
        return predictionRepository.findTopByOrderByIdDesc();
    }
}

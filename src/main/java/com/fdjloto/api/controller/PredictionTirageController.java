package com.fdjloto.api.controller;

import com.fdjloto.api.model.PredictionTirageModel;
import com.fdjloto.api.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/predictions")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class PredictionTirageController {

    private final PredictionService predictionService;

    @Autowired
    public PredictionTirageController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @GetMapping("/latest")
    public ResponseEntity<PredictionTirageModel> getLatestPrediction() {
        PredictionTirageModel prediction = predictionService.getLatestPrediction();
        return prediction != null ? ResponseEntity.ok(prediction) : ResponseEntity.notFound().build();
    }
}



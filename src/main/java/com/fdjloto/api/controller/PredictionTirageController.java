package com.fdjloto.api.controller;

import com.fdjloto.api.model.PredictionTirageModel;
import com.fdjloto.api.service.PredictionService;
import com.fdjloto.api.service.PredictionTirageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/predictions")
@CrossOrigin(origins = "http://127.0.0.1:5500") // 🔥 Autorise CORS pour Live Server
public class PredictionTirageController {

    private final PredictionService predictionService;
    private final PredictionTirageService predictionTirageService;

    public PredictionTirageController(PredictionService predictionService, PredictionTirageService predictionTirageService) {
        this.predictionService = predictionService;
        this.predictionTirageService = predictionTirageService;
    }

    // 🔹 Générer une nouvelle prédiction et renvoyer les résultats
    @PostMapping("/generate")
    public ResponseEntity<PredictionTirageModel> generateNewPrediction() {
        PredictionTirageModel prediction = predictionTirageService.generatePrediction();
        return prediction != null ? ResponseEntity.ok(prediction) : ResponseEntity.badRequest().body(null);
    }

    // 🔹 Récupérer la dernière prédiction enregistrée
    @GetMapping("/latest")
    public ResponseEntity<PredictionTirageModel> getLatestPrediction() {
        PredictionTirageModel prediction = predictionService.getLatestPrediction();
        return prediction != null ? ResponseEntity.ok(prediction) : ResponseEntity.notFound().build();
    }
}


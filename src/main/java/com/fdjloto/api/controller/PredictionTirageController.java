package com.fdjloto.api.controller;

import com.fdjloto.api.model.PredictionTirageModel;
import com.fdjloto.api.service.PredictionService;
import com.fdjloto.api.service.PredictionTirageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/predictions")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class PredictionTirageController {

    private final PredictionService predictionService;
    private final PredictionTirageService predictionTirageService;

    @Autowired
    public PredictionTirageController(PredictionService predictionService, PredictionTirageService predictionTirageService) {
        this.predictionService = predictionService;
        this.predictionTirageService = predictionTirageService;
    }

//     @GetMapping("/latest")
//     public ResponseEntity<PredictionTirageModel> getLatestPrediction() {
//         PredictionTirageModel prediction = predictionService.getLatestPrediction();
//         return prediction != null ? ResponseEntity.ok(prediction) : ResponseEntity.notFound().build();
//     }
// }

// 🔹 Récupérer la dernière prédiction
    @GetMapping("/latest")
    public ResponseEntity<PredictionTirageModel> getLatestPrediction() {
        PredictionTirageModel prediction = predictionService.getLatestPrediction();

        if (prediction == null) {
            // Si aucune prédiction n'existe, on la génère
            predictionTirageService.generatePrediction();
            prediction = predictionService.getLatestPrediction();
        }

        return prediction != null ? ResponseEntity.ok(prediction) : ResponseEntity.notFound().build();
    }

    // 🔹 Générer une nouvelle prédiction manuellement
    @PostMapping("/generate")
    public ResponseEntity<String> generateNewPrediction() {
        predictionTirageService.generatePrediction();
        return ResponseEntity.ok("✅ Nouvelle prédiction générée avec succès !");
    }
}

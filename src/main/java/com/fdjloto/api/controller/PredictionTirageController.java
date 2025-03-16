package com.fdjloto.api.controller;

import com.fdjloto.api.model.PredictionTirageModel;
import com.fdjloto.api.service.PredictionService;
import com.fdjloto.api.service.PredictionTirageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * **Controller for managing lottery predictions.**
 */
@RestController
@RequestMapping("/api/predictions")
@Tag(name = "Lottery Predictions", description = "Endpoints for generating and retrieving lottery predictions.")
@CrossOrigin(origins = "http://127.0.0.1:5500") // 🔥 Enables CORS for Live Server
public class PredictionTirageController {

    private final PredictionService predictionService;
    private final PredictionTirageService predictionTirageService;

    // ✅ Constructor-based dependency injection
    public PredictionTirageController(PredictionService predictionService, PredictionTirageService predictionTirageService) {
        this.predictionService = predictionService;
        this.predictionTirageService = predictionTirageService;
    }

    /**
     * **🔮 Generate a new lottery prediction.**
     *
     * @return **200 OK** - The generated prediction if successful.
     * @return **400 BAD REQUEST** - If prediction generation fails.
     */
    @Operation(
        summary = "Generate a new lottery prediction",
        description = "Creates a new prediction based on historical lottery data and returns the generated results."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Prediction successfully generated."),
        @ApiResponse(responseCode = "400", description = "Failed to generate prediction.")
    })
    @PostMapping("/generate")
    public ResponseEntity<PredictionTirageModel> generateNewPrediction() {
        PredictionTirageModel prediction = predictionTirageService.generatePrediction();
        return prediction != null ? ResponseEntity.ok(prediction) : ResponseEntity.badRequest().body(null);
    }

    /**
     * **📊 Retrieve the latest saved lottery prediction.**
     *
     * @return **200 OK** - The latest prediction if available.
     * @return **404 NOT FOUND** - If no prediction data is found.
     */
    @Operation(
        summary = "Get the latest lottery prediction",
        description = "Fetches the most recent prediction stored in the system."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Latest prediction retrieved successfully."),
        @ApiResponse(responseCode = "404", description = "No prediction found.")
    })
    @GetMapping("/latest")
    public ResponseEntity<PredictionTirageModel> getLatestPrediction() {
        PredictionTirageModel prediction = predictionService.getLatestPrediction();
        return prediction != null ? ResponseEntity.ok(prediction) : ResponseEntity.notFound().build();
    }
}

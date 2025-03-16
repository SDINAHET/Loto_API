package com.fdjloto.api.controller;

import com.fdjloto.api.dto.LotoResultDTO;
import com.fdjloto.api.model.Historique20Result;
import com.fdjloto.api.service.Historique20Service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * **Controller for retrieving historical lottery results.**
 */
@CrossOrigin(origins = "http://127.0.0.1:5500") // 🔥 Allows CORS for Live Server
@RestController
@RequestMapping("/api/historique")
@Tag(name = "Lottery History", description = "Endpoints for retrieving historical lottery results.")
// @CrossOrigin(origins = "*") // Enables requests from the frontend
public class Historique20Controller {

    private final Historique20Service historique20Service;

    /**
     * **Constructor for injecting the Historique20Service dependency.**
     *
     * @param historique20Service Service handling historical lottery results.
     */
    public Historique20Controller(Historique20Service historique20Service) {
        this.historique20Service = historique20Service;
    }

    /**
     * **Retrieve the last 20 lottery results.**
     *
     * This endpoint fetches the most recent 20 lottery results from the database, formatted properly.
     *
     * @return **200 OK** - A list of the last 20 lottery results.
     */
    @Operation(summary = "Get last 20 lottery results", description = "Fetches the latest 20 lottery results with formatted dates.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the last 20 results."),
        @ApiResponse(responseCode = "500", description = "Internal server error while fetching results.")
    })
    @GetMapping("/last20")
    public List<LotoResultDTO> getLast20Results() {
        List<Historique20Result> results = historique20Service.getLast20Results();
        return results.stream()
                .map(result -> new LotoResultDTO(
                        result.getId(),
                        result.getDateDeTirage(), // ✅ Keeping Date, as @JsonFormat handles formatting
                        // result.getCombinaisonGagnante(),
                        result.getBoule1(),
                        result.getBoule2(),
                        result.getBoule3(),
                        result.getBoule4(),
                        result.getBoule5(),
                        result.getNumeroChance()))
                .toList(); // ✅ SonarLint recommends using `.toList()` instead of `Collectors.toList()`
    }
}

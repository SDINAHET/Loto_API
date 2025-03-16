package com.fdjloto.api.controller;

import com.fdjloto.api.model.Historique20Detail;
import com.fdjloto.api.service.Historique20DetailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

/**
 * **Controller for managing historical lottery details.**
 */
@CrossOrigin(origins = "http://127.0.0.1:5500") // 🔥 Allows CORS for Live Server
@RestController
@RequestMapping("/api/historique/last20/Detail")
@Tag(name = "Lottery Details", description = "Endpoints for retrieving detailed historical lottery results.")
public class Historique20DetailController {

    private final Historique20DetailService lotoService;

    /**
     * **Constructor to inject the Historique20DetailService dependency.**
     *
     * @param lotoService Service handling historical lottery details.
     */
    public Historique20DetailController(Historique20DetailService lotoService) {
        this.lotoService = lotoService;
    }

    /**
     * **🔹 Search for a single lottery draw by a specific date.**
     *
     * @param date The date of the draw to retrieve (format: yyyy-MM-dd).
     * @return **200 OK** - The lottery draw details if found.
     * **404 NOT FOUND** - If no draw exists for the given date.
     */
    @Operation(summary = "Retrieve a lottery draw by date",
               description = "Fetches the lottery draw details for a specific date.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the draw details."),
        @ApiResponse(responseCode = "404", description = "No draw found for the given date.")
    })
    @GetMapping("/tirage/{date}")
    public ResponseEntity<Historique20Detail> getTirage(@PathVariable String date) {
        Optional<Historique20Detail> result = lotoService.getTirageByDate(date);
        return result.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    /**
     * **🔍 Search for lottery draws within a date range.**
     *
     * @param startDate The start date of the search range (format: yyyy-MM-dd).
     * @param endDate (Optional) The end date of the search range.
     * If not provided, it searches only for `startDate`.
     * @return **200 OK** - A list of lottery draws within the specified date range.
     * **404 NOT FOUND** - If no draws exist in the given range.
     */
    @Operation(summary = "Retrieve lottery draws within a date range",
               description = "Fetches all lottery draws between the specified start and end dates.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the lottery draws."),
        @ApiResponse(responseCode = "404", description = "No draws found in the specified date range.")
    })
    @GetMapping("/tirages")
    public ResponseEntity<List<Historique20Detail>> getTiragesParPlageDeDates(
            @RequestParam String startDate,
            @RequestParam(required = false) String endDate) {

        List<Historique20Detail> result;

        // ✅ If endDate is null, only search for the given startDate
        if (endDate == null || endDate.isEmpty()) {
            result = lotoService.getTiragesParPlageDeDates(startDate, startDate); // Search for a single day
        } else {
            result = lotoService.getTiragesParPlageDeDates(startDate, endDate);
        }

        // Return 404 if no results are found
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);
    }
}

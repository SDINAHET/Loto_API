package com.fdjloto.api.controller;

import com.fdjloto.api.model.LotoResult;
import com.fdjloto.api.repository.LotoRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Locale;
import java.util.Optional;

/**
 * **Controller for managing lottery draws (Tirages).**
 */
@RestController
@RequestMapping("/api/tirages")
@Tag(name = "Loto Draw Management", description = "Endpoints for retrieving available draw dates and searching draws by date range.")
// @CrossOrigin(origins = "*") // Allows requests from any origin
@CrossOrigin(origins = "http://127.0.0.1:5500") // 🔥 Enables CORS for Live Server
public class TirageController {

    private final LotoRepository lotoRepository;

    // ✅ Proper definition of DATE_FORMAT for formatting dates
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEEE dd/MM/yyyy", Locale.FRANCE);

    public TirageController(LotoRepository lotoRepository) {
        this.lotoRepository = lotoRepository;
    }

    /**
     * 🔹 Retrieves all available draw dates in descending order.
     * 🔹 Formats the date in "EEEE dd/MM/yyyy" format (e.g., "Friday 15/03/2025").
     * 🔹 Removes duplicates and ensures the list is sorted.
     *
     * @return **200 OK** - List of formatted draw dates.
     */
    @Operation(summary = "Retrieve available draw dates", description = "Returns a sorted list of unique draw dates in descending order.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successfully retrieved available draw dates.")
    })
    @GetMapping("/dates")
    public List<String> getAvailableDates() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE dd/MM/yyyy", Locale.FRANCE);

        return lotoRepository.findAll().stream()
                .map(LotoResult::getDateDeTirage) // Extracts the draw date
                .filter(date -> date != null) // Avoids null values
                .distinct() // Removes duplicates
                .sorted((d1, d2) -> d2.compareTo(d1)) // Sorts in descending order (most recent first)
                .map(dateFormat::format) // Converts the date to "dd/MM/yyyy" format
                .collect(Collectors.toList());
    }

    /**
     * ✅ Retrieves lottery draws within a specific date range.
     * ✅ If `startDate` is not provided, it defaults to the latest draw date.
     * ✅ Ensures `endDate` is not earlier than `startDate` to maintain a valid range.
     *
     * @param startDate The start date (optional, defaults to the latest draw date).
     * @param endDate   The end date (optional, adjusted if earlier than `startDate`).
     * @return **200 OK** - List of lottery draws within the specified period.
     * @return **400 BAD REQUEST** - If date format is invalid.
     * @return **404 NOT FOUND** - If no draws are found in the given range.
     */
    @Operation(summary = "Retrieve draws by date range", description = "Searches for lottery draws between the given start and end dates.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successfully retrieved draws for the given period."),
        @ApiResponse(responseCode = "400", description = "Invalid date format. Use 'dd/MM/yyyy'."),
        @ApiResponse(responseCode = "404", description = "No draws found for the given date range.")
    })
    @GetMapping
    public List<LotoResult> getTiragesParPeriode(
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date endDate) {

        // Retrieve the most recent draw date available
        Optional<Date> lastTirageDate = lotoRepository.findAll().stream()
                .map(LotoResult::getDateDeTirage)
                .max(Date::compareTo);

        // If `startDate` is not provided, set it to the latest draw date
        if (startDate == null && lastTirageDate.isPresent()) {
            startDate = lastTirageDate.get();
        }

        // Ensure `endDate` is not earlier than `startDate`, adjust if necessary
        if (endDate != null && startDate != null && endDate.before(startDate)) {
            endDate = startDate;
        }

        // Retrieve lottery draws between the given date range
        return lotoRepository.findByDateDeTirageBetween(startDate, endDate);
    }

}

package com.fdjloto.api.controller;

import com.fdjloto.api.dto.GainResultDTO;
import com.fdjloto.api.dto.TicketGainDTO;
import com.fdjloto.api.model.TicketGain;
import com.fdjloto.api.repository.TicketGainRepository;
import com.fdjloto.api.service.GainCalculationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

/**
 * **Ticket Gains Management (`GainController`)**
 *
 * This controller provides endpoints for calculating and retrieving ticket winnings.
 */
@RestController
@RequestMapping("/api/gains")
@Tag(name = "Ticket Gains Management", description = "Endpoints for managing tickets, including searching and calculating winnings.")
@SecurityRequirement(name = "bearerAuth")
@SecurityRequirement(name = "jwtCookieAuth")
@CrossOrigin(
    origins = "http://127.0.0.1:5500",
    allowCredentials = "true",
    allowedHeaders = "*",
    methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}
)
public class GainController {

    private static final Logger logger = LoggerFactory.getLogger(GainController.class);

    private final GainCalculationService gainCalculationService;
    private final TicketGainRepository ticketGainRepository;

    // ✅ Constructor-based dependency injection
    public GainController(GainCalculationService gainCalculationService, TicketGainRepository ticketGainRepository) {
        this.gainCalculationService = gainCalculationService;
        this.ticketGainRepository = ticketGainRepository;
    }

    /**
     * **🚀 Calculate and save ticket winnings.**
     *
     * This endpoint triggers the calculation of winnings for all tickets.
     *
     * @return **200 OK** - Returns a list of calculated winnings as DTOs.
     */
    @Operation(summary = "Calculate ticket winnings", description = "Triggers the calculation of ticket winnings and returns the results.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Winnings calculated successfully.")
    })
    @GetMapping("/calculate")
    public ResponseEntity<List<GainResultDTO>> calculateGains() {
        logger.info("🔄 Starting winnings calculation...");
        List<GainResultDTO> results = gainCalculationService.calculerGains();
        logger.info("✅ Winnings calculation completed! {} results generated.", results.size());
        return ResponseEntity.ok(results);
    }

    /**
     * **Retrieve all ticket winnings.**
     *
     * This endpoint fetches all winnings stored in the database.
     *
     * @return **200 OK** - Returns a list of ticket winnings in DTO format.
     */
    @Operation(summary = "Get all ticket winnings", description = "Retrieves all ticket winnings stored in the database.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of ticket winnings retrieved successfully.")
    })
    @GetMapping
    public List<TicketGainDTO> getAllGains() {
        return ticketGainRepository.findAll()
            .stream()
            .map(TicketGainDTO::new) // ❗ Convert to DTO to avoid exposing User entity
            .toList();
    }

    /**
     * **Retrieve winnings for a specific ticket.**
     *
     * This endpoint fetches winnings for a specific ticket based on its ID.
     *
     * @param ticketId The ID of the ticket to search for.
     * @return **200 OK** - Returns the winnings associated with the given ticket.
     * @return **404 Not Found** - If the ticket winnings are not found.
     */
    @Operation(summary = "Get winnings by ticket ID", description = "Retrieves winnings for a specific ticket using its unique ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Ticket winnings retrieved successfully."),
        @ApiResponse(responseCode = "404", description = "Ticket winnings not found.")
    })
    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketGainDTO> getGainByTicketId(@PathVariable String ticketId) {
        return ticketGainRepository.findByTicketId(ticketId)
            .map(TicketGainDTO::new)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

}

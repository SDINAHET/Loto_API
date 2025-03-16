package com.fdjloto.api.controller;

import com.fdjloto.api.service.LotoScraperService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * **Controller for handling lottery scraping operations.**
 */
@RestController
@RequestMapping("/api/loto")
@Tag(name = "Lottery Scraper", description = "Endpoints for triggering the lottery web scraping process.")
// @CrossOrigin(origins = "*") // Allows requests from any frontend
@CrossOrigin(origins = "http://127.0.0.1:5500") // 🔥 Enables CORS for Live Server
public class LotoController {

    @Autowired
    private LotoScraperService lotoScraperService; // ✅ Injecting the scraping service

    /**
     * **🔄 Endpoint to trigger the web scraping process for lottery results.**
     *
     * @return **200 OK** - A success message indicating that the scraping has started.
     */
    @Operation(summary = "Start lottery web scraping",
               description = "Triggers the scraping process to fetch lottery results from an external source.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Scraping successfully started.")
    })
    @GetMapping("/scrape")
    public String triggerScraping() {
        lotoScraperService.scrapeData();
        return "Scraping successfully started!";
    }
}

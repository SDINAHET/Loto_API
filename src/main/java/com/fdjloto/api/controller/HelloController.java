package com.fdjloto.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * **Health Check Controller (`HelloController`)**
 *
 * Provides a simple endpoint to check if the API is running.
 */
// @CrossOrigin(origins = "http://127.0.0.1:5500") // 🔥 Allows CORS for Live Server
@RestController
@RequestMapping("/api")
@Tag(name = "Health Check", description = "Simple endpoint to verify if the API is running.")
public class HelloController {

    /**
     * **Check API health status**
     *
     * This endpoint returns a confirmation message indicating that the API is up and running.
     *
     * @return **200 OK** - Returns a confirmation message that the API is operational.
     */
    @Operation(summary = "Check API health", description = "Returns a message confirming that the API is running.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "API is running successfully.")
    })
    @GetMapping("/hello")
    public String hello() {
        return "✅ Loto API is running without a database!";
    }
}

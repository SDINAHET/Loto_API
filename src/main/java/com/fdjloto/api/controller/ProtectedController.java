package com.fdjloto.api.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.HashMap;
import java.util.Map;

/**
 * **Controller for accessing protected API endpoints.**
 * Requires JWT authentication.
 */
@RestController
@RequestMapping("/api/protected")
@Tag(name = "Protected Resources", description = "Endpoints that require authentication via JWT.")
@SecurityRequirement(name = "bearerAuth")  // 🔥 Swagger recognizes this controller as secured
@CrossOrigin(origins = "http://127.0.0.1:5500") // 🔥 Enables CORS for Live Server
// @CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})

public class ProtectedController {

    /**
     * **🔑 Retrieves authenticated user information from JWT.**
     *
     * @param jwt The JSON Web Token (JWT) containing user details.
     * @return **200 OK** - Returns user information extracted from JWT.
     * @return **401 UNAUTHORIZED** - If the token is invalid or missing.
     */
    @Operation(
        summary = "Get authenticated user info",
        description = "Returns user details extracted from the JWT, including username and roles."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successfully retrieved user information."),
        @ApiResponse(responseCode = "401", description = "Unauthorized: Invalid or missing JWT token.")
    })
    @GetMapping("/userinfo")
    public ResponseEntity<Map<String, Object>> getUserInfo(@AuthenticationPrincipal Jwt jwt) {
        if (jwt == null) {
            // ❌ If the token is invalid or missing, return an unauthorized response
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Invalid or missing JWT token"));
        }

        // ✅ If authenticated, return user details extracted from the JWT
        return ResponseEntity.ok(Map.of(
            "message", "Bienvenue " + jwt.getSubject(),
            "username", jwt.getSubject(),
            "roles", jwt.getClaim("roles"),
            "token_valid", "✅"
        ));
    }

}

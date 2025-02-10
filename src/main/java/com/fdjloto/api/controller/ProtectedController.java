package com.fdjloto.api.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/protected")
// @SecurityRequirement(name = "bearerAuth")  // 🔥 Swagger sait que ce contrôleur est sécurisé
public class ProtectedController {

    @GetMapping("/userinfo")
    public ResponseEntity<Map<String, String>> getUserInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        // Vérification de l'authentification de l'utilisateur
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;

            Map<String, String> response = new HashMap<>();
            response.put("message", "Bienvenue " + userDetails.getUsername());
            response.put("username", userDetails.getUsername());
            response.put("roles", userDetails.getAuthorities().toString());

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Utilisateur non authentifié"));
        }
    }
}


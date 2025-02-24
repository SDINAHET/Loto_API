package com.fdjloto.api.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class LogoutController {

    @PostMapping("/logout")
    public ResponseEntity<Object> logout() {
        ResponseCookie jwtCookie = ResponseCookie.from("jwtToken", "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0)
                .sameSite("None")
                .build();

        // ✅ Retourner un Map avec ResponseEntity<Object>
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(Map.of("message", "✅ Déconnexion réussie.", "redirect", "login.html"));
    }
}


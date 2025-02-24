package com.fdjloto.api.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import java.util.Date;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${app.jwtSecret}") // Charge la clé depuis application.properties
    private String secretKey;

    @Value("${app.jwtExpirationMs}") // Charge le délai d'expiration depuis application.properties
    private int jwtExpirationMs;

    // 🔐 Méthode privée pour récupérer la clé de signature correcte
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    // 🔑 Générer un token JWT avec le rôle
    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email) // ✅ On passe maintenant l'email
                .claim("role", role) // 🔥 Ajout du rôle dans le token
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs)) // Token valide 24h
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ Valider un token JWT et récupérer le rôle
    public String validateToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey()) // ✅ Utilisation de la clé de signature
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject(); // Récupère l'email (sub) depuis le token
        } catch (Exception e) {
            logger.error("❌ Erreur de validation du token : {}", e.getMessage());
            return null;
        }
    }

    // 🔓 Récupérer le rôle depuis le token
    public String getRoleFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("role", String.class); // Récupère le rôle depuis les claims
    }
}

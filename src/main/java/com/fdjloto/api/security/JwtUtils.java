package com.fdjloto.api.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

@Component
public class JwtUtils {


    @Value("${app.jwtSecret}") // 🔥 Récupère la clé JWT depuis application.properties
    private String jwtSecret;

    @Value("${app.jwtExpirationMs}") // 🔥 Récupère l'expiration depuis application.properties
    private int jwtExpirationMs;

    private static final String SECRET_KEY = "your_super_secret_key_that_should_be_at_least_32_characters_long";
    private static final SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    private static final long EXPIRATION_TIME = 86400000; // 1 jour en millisecondes

    // ✅ Génération du Token JWT avec HS256
    public String generateJwtToken(Authentication authentication) {
        // Récupère le rôle de l'utilisateur
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("roles", roles) // ✅ Ajout du role dans JWT)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ Extraction du nom d'utilisateur (email) depuis le token JWT
    public String getUserFromJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // ✅ Extraction des rôles depuis le Token JWT
    public List<String> getRolesFromJwtToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.get("roles", List.class);
    }


    public boolean validateJwtToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            System.out.println("Invalid JWT Token: " + e.getMessage());
            return false;
        }
    }


    // ✅ Ajout de la méthode getUserNameFromJwtToken
    public String getUserNameFromJwtToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject(); // 🔥 Retourne l'email ou l'username
    }
}

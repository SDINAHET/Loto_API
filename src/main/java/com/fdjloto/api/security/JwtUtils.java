package com.fdjloto.api.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.crypto.SecretKey;

/**
 * **JWT Utility Class (`JwtUtils`)**
 *
 * This class provides helper methods for generating, parsing, and validating JWT tokens.
 * It is responsible for signing the tokens using the HS256 algorithm and extracting claims such as user roles.
 */
@Component
public class JwtUtils {

    // 🔥 Retrieves the JWT secret key from application.properties
    @Value("${app.jwtSecret}")
    private String jwtSecret;

    // 🔥 Retrieves the JWT expiration time from application.properties
    @Value("${app.jwtExpirationMs}")
    private int jwtExpirationMs;

    // 🔐 Static secret key used for signing the JWT (should be at least 32 characters long)
    private static final String SECRET_KEY = "your_super_secret_key_that_should_be_at_least_32_characters_long";

    // 🔐 Generates a cryptographic key for HS256 signing
    private static final SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    // ⏳ Token expiration time (1 day in milliseconds)
    private static final long EXPIRATION_TIME = 86400000;

    /**
     * **Generates a JWT token for authentication**
     *
     * @param authentication The Spring Security authentication object containing the user's details.
     * @return A signed JWT token.
     */
    public String generateJwtToken(Authentication authentication) {
        // 🔍 Extracts the roles of the authenticated user
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return Jwts.builder()
                .setSubject(authentication.getName()) // 🆔 User identifier (usually email)
                .claim("roles", roles) // 🎭 Adds user roles as a claim
                .setIssuedAt(new Date()) // 📅 Sets token issue date
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // ⏳ Sets token expiration
                .signWith(secretKey, SignatureAlgorithm.HS256) // 🔐 Signs the token using HS256
                .compact();
    }

    /**
     * **Extracts the username (email) from the JWT token**
     *
     * @param token The JWT token.
     * @return The username (email) stored in the token.
     */
    public String getUserFromJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey) // 🔑 Validates the signature using the secret key
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject(); // 📌 Retrieves the "sub" (subject) claim (username or email)
    }

    /**
     * **Extracts user roles from the JWT token**
     *
     * @param token The JWT token.
     * @return A list of roles assigned to the user.
     */
    public List<String> getRolesFromJwtToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey) // 🔑 Validates the signature using the secret key
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.get("roles", List.class); // 🎭 Extracts the "roles" claim as a list
    }

    /**
     * **Validates the JWT token**
     *
     * This method checks if the token is correctly signed and not expired.
     *
     * @param token The JWT token to validate.
     * @return `true` if the token is valid, `false` otherwise.
     */
    public boolean validateJwtToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey) // 🔑 Validates the signature using the secret key
                    .build()
                    .parseClaimsJws(token);
            return true; // ✅ Token is valid
        } catch (JwtException e) { // ❌ Handles invalid or expired token exceptions
            System.out.println("Invalid JWT Token: " + e.getMessage());
            return false;
        }
    }

    /**
     * **Extracts the username (email) from the JWT token**
     *
     * This method is an alternative to `getUserFromJwtToken` using a different approach.
     *
     * @param token The JWT token.
     * @return The username (email) stored in the token.
     */
    public String getUserNameFromJwtToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret) // 🔑 Uses `jwtSecret` instead of `secretKey`
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject(); // 📌 Retrieves the "sub" (subject) claim
    }
}

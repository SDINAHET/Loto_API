package com.fdjloto.api.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
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

    public String generateJwtToken(Authentication authentication) {
        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public String getUserFromJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
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
    // public static void main(String[] args) {
    //     JwtUtils jwtUtils = new JwtUtils();

    //     // Simulation d'un utilisateur
    //     String token = jwtUtils.generateJwtToken(() -> "testUser");

    //     System.out.println("🔹 Token généré : " + token);
    //     System.out.println("🔹 Utilisateur extrait du token : " + jwtUtils.getUserFromJwtToken(token));
    //     System.out.println("🔹 Token valide ? " + jwtUtils.validateJwtToken(token));
    // }

    // ✅ Ajout de la méthode getUserNameFromJwtToken
    public String getUserNameFromJwtToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject(); // 🔥 Retourne l'email ou l'username
    }
}

// package com.fdjloto.api.security;

// import io.jsonwebtoken.*;
// import io.jsonwebtoken.security.Keys;
// import org.springframework.security.core.Authentication;
// import org.springframework.stereotype.Component;
// import org.springframework.beans.factory.annotation.Value;

// import java.nio.charset.StandardCharsets;
// import java.util.Date;
// import javax.crypto.SecretKey;

// @Component
// public class JwtUtils {

//     @Value("${app.jwtSecret}") // 🔥 Récupère la clé JWT depuis application.properties
//     private String jwtSecret;

//     @Value("${app.jwtExpirationMs}") // 🔥 Récupère l'expiration depuis application.properties
//     private int jwtExpirationMs;

//     // ⚠️ Utilisation CONSISTANTE de jwtSecret pour la clé
//     private SecretKey secretKey;

//     // 📌 Initialisation du secret dans le constructeur
//     public JwtUtils(@Value("${app.jwtSecret}") String jwtSecret) {
//         this.secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
//     }

//     // ✅ Génération du Token JWT avec HS256
//     public String generateJwtToken(Authentication authentication) {
//         return Jwts.builder()
//                 .setSubject(authentication.getName())
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
//                 .signWith(secretKey, SignatureAlgorithm.HS256)
//                 .compact();
//     }

//     // ✅ Extraction du nom d'utilisateur (email) depuis le token JWT
//     public String getUserFromJwtToken(String token) {
//         return Jwts.parserBuilder()
//                 .setSigningKey(secretKey)
//                 .build()
//                 .parseClaimsJws(token)
//                 .getBody()
//                 .getSubject();
//     }

//     // ✅ Validation du Token JWT
//     public boolean validateJwtToken(String token) {
//         try {
//             Jwts.parserBuilder()
//                 .setSigningKey(secretKey)
//                 .build()
//                 .parseClaimsJws(token);
//             return true;
//         } catch (ExpiredJwtException e) {
//             System.out.println("🔴 Token expiré : " + e.getMessage());
//         } catch (UnsupportedJwtException e) {
//             System.out.println("🔴 Token non supporté : " + e.getMessage());
//         } catch (MalformedJwtException e) {
//             System.out.println("🔴 Token mal formé : " + e.getMessage());
//         } catch (SignatureException e) {
//             System.out.println("🔴 Signature invalide : " + e.getMessage());
//         } catch (IllegalArgumentException e) {
//             System.out.println("🔴 Token vide ou null : " + e.getMessage());
//         }
//         return false;
//     }
// }

// package com.fdjloto.api.security;

// import io.jsonwebtoken.*;
// import io.jsonwebtoken.security.Keys;
// import org.springframework.security.core.Authentication;
// import org.springframework.stereotype.Component;
// import org.springframework.beans.factory.annotation.Value;

// import java.nio.charset.StandardCharsets;
// import java.util.Date;
// import javax.crypto.SecretKey;

// @Component
// public class JwtUtils {

//     @Value("${app.jwtSecret}")
//     private String jwtSecret;

//     @Value("${app.jwtExpirationMs}")
//     private int jwtExpirationMs;

//     // ⚠️ Utilisation CONSISTANTE de jwtSecret pour la clé
//     private SecretKey secretKey;

//     public JwtUtils(@Value("${app.jwtSecret}") String jwtSecret) {
//         this.secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
//     }

//     // ✅ Génération du Token JWT avec HS256
//     public String generateJwtToken(Authentication authentication) {
//         return Jwts.builder()
//                 .setSubject(authentication.getName())
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
//                 .signWith(secretKey, SignatureAlgorithm.HS256)
//                 .compact();
//     }

//     // ✅ Extraction du nom d'utilisateur (email) depuis le token JWT
//     public String getUserFromJwtToken(String token) {
//         return Jwts.parserBuilder()
//                 .setSigningKey(secretKey)
//                 .build()
//                 .parseClaimsJws(token)
//                 .getBody()
//                 .getSubject();
//     }

//     // ✅ Validation du Token JWT
//     public boolean validateJwtToken(String token) {
//         try {
//             Jwts.parserBuilder()
//                 .setSigningKey(secretKey)
//                 .build()
//                 .parseClaimsJws(token);
//             return true;
//         } catch (ExpiredJwtException e) {
//             System.out.println("🔴 Token expiré : " + e.getMessage());
//         } catch (UnsupportedJwtException e) {
//             System.out.println("🔴 Token non supporté : " + e.getMessage());
//         } catch (MalformedJwtException e) {
//             System.out.println("🔴 Token mal formé : " + e.getMessage());
//         } catch (SignatureException e) {
//             System.out.println("🔴 Signature invalide : " + e.getMessage());
//         } catch (IllegalArgumentException e) {
//             System.out.println("🔴 Token vide ou null : " + e.getMessage());
//         }
//         return false;
//     }
// }


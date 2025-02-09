// package com.fdjloto.security;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import io.jsonwebtoken.security.Keys;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.stereotype.Service;

// import java.security.Key;
// import java.util.Date;
// import java.util.function.Function;
// import java.time.Instant;

// @Service
// public class JWTUtils {
//     private static final String SECRET_KEY = "supersecretkeysupersecretkeysupersecretkey"; // Clé de 256 bits min

//     private Key getSigningKey() {
//         return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
//     }

//     public String generateToken(UserDetails userDetails) {
//         return Jwts.builder()
//                 .setSubject(userDetails.getUsername())
//                 .setIssuedAt(Date.from(Instant.now()))
//                 .setExpiration(Date.from(Instant.now().plusSeconds(1000 * 60 * 60 * 10)))
//                 .signWith(getSigningKey(), SignatureAlgorithm.HS256)
//                 .compact();
//     }

//     public String extractUsername(String token) {
//         return extractClaim(token, Claims::getSubject);
//     }

//     public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//         final Claims claims = extractAllClaims(token);
//         return claimsResolver.apply(claims);
//     }

//     private Claims extractAllClaims(String token) {
//         return Jwts.parserBuilder()
//                 .setSigningKey(getSigningKey())
//                 .build()
//                 .parseClaimsJws(token)
//                 .getBody();
//     }

//     public boolean validateToken(String token, UserDetails userDetails) {
//         final String username = extractUsername(token);
//         return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//     }

//     private boolean isTokenExpired(String token) {
//         return extractClaim(token, Claims::getExpiration).before(new Date());
//     }
// }


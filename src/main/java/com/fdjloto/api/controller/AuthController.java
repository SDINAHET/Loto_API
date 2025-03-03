package com.fdjloto.api.controller;

import com.fdjloto.api.model.LoginRequest;
import com.fdjloto.api.model.User;
import com.fdjloto.api.security.JwtUtils;
import com.fdjloto.api.service.UserService;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.Cookie;
import java.util.UUID;
import java.util.Map;
import java.util.HashMap;

import com.fdjloto.api.payload.MessageResponse;


// @CrossOrigin(origins = "http://127.0.0.1:5500") // 🔥 Autorise CORS pour Live Server
@CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    // Ajouter en haut de la classe AuthController
    private static final String JWT_COOKIE_NAME = "jwtToken";


    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserService userService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    // @PostMapping("/login4")
    // public ResponseEntity<String> authenticateUser(@RequestParam String email, @RequestParam String password) {
    //     Authentication authentication = authenticationManager.authenticate(
    //             new UsernamePasswordAuthenticationToken(email, password)
    //     );
    //     SecurityContextHolder.getContext().setAuthentication(authentication);
    //     String jwt = jwtUtils.generateJwtToken(authentication);
    //     return ResponseEntity.ok(jwt);
    // }

    @PostMapping("/login4")
    public ResponseEntity<String> authenticateUser(@RequestParam String email, @RequestParam String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // ✅ Récupération de l'utilisateur et de son UUID
        User user = userService.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec l'email: " + email));
        UUID userId = UUID.fromString(user.getId());

        // ✅ Génération du Token avec `userId`
        String jwt = jwtUtils.generateJwtToken(authentication, userId);
        return ResponseEntity.ok(jwt);
    }


    // @PostMapping("/login3")
    // public ResponseEntity<Map<String, String>> authenticateUserWithCookie(
    //         @RequestBody LoginRequest loginRequest,
    //         HttpServletResponse response
    // ) {
    //     try {
    //         // 🔐 Authentification
    //         Authentication authentication = authenticationManager.authenticate(
    //                 new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
    //         );
    //         SecurityContextHolder.getContext().setAuthentication(authentication);

    //         // 🔑 Génération du Token JWT
    //         String jwt = jwtUtils.generateJwtToken(authentication);

    //         // 🍪 Configuration du Cookie Sécurisé
    //         Cookie jwtCookie = new Cookie(JWT_COOKIE_NAME, jwt);
    //         jwtCookie.setHttpOnly(false);
    //         jwtCookie.setSecure(false); // 🔒 À mettre à true en production
    //         jwtCookie.setPath("/");
    //         jwtCookie.setMaxAge(10 * 60); // 10min
    //         // jwtCookie.setDomain("localhost");
    //         // jwtCookie.setSameSite("Lax"); // Pour la gestion de CORS

    //         // 🔥 Configuration SameSite pour le CORS
    //         // 🚨 Utilisez "None" en production (HTTPS requis)
    //         jwtCookie.setDomain("localhost"); // Correspond au domaine du frontend
    //         response.addHeader("Set-Cookie", String.format("%s=%s; HttpOnly; Path=/; Max-Age=3600; SameSite=None; Secure=%b",
    //             JWT_COOKIE_NAME, jwt, false)); // ⚠️ false pour HTTP en local, true en HTTPS

    //         response.addCookie(jwtCookie);

    //         // ✅ Réponse avec Token et Message
    //         Map<String, String> responseBody = new HashMap<>();
    //         responseBody.put("token", jwt);
    //         responseBody.put("message", "Connexion réussie");
    //         System.out.println("🔑 JWT généré : " + jwt);


    //         return ResponseEntity.ok(responseBody);

    //     } catch (Exception e) {
    //         // ❌ En cas d'erreur d'authentification
    //         Map<String, String> errorResponse = new HashMap<>();
    //         errorResponse.put("message", "Échec de la connexion");

    //         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    //     }
    // }

    // @PostMapping("/login3")
    // public ResponseEntity<Map<String, String>> authenticateUserWithCookieAndLocalStorage(
    //         @RequestBody LoginRequest loginRequest,
    //         HttpServletResponse response
    // ) {
    //     try {
    //         // 🔐 Authentification
    //         Authentication authentication = authenticationManager.authenticate(
    //                 new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
    //         );
    //         SecurityContextHolder.getContext().setAuthentication(authentication);

    //         // 🔑 Génération du Token JWT
    //         String jwt = jwtUtils.generateJwtToken(authentication);

    //         // 🍪 1. Stockage dans un Cookie sécurisé
    //         // Cookie jwtCookie = new Cookie("auth_token", jwt);
    //         Cookie jwtCookie = new Cookie("jwtToken", jwt);
    //         jwtCookie.setHttpOnly(true);  // HttpOnly pour la sécurité contre les attaques XSS
    //         jwtCookie.setSecure(false);   // ⚠️ Utilisez 'true' en production (HTTPS requis)
    //         jwtCookie.setPath("/");
    //         jwtCookie.setMaxAge(10 * 60); // Expiration : 10 minutes
    //         response.addCookie(jwtCookie);

    //         // 🔥 2. Configuration SameSite pour le CORS
    //         // 🚨 Utilisez "None" en production (HTTPS requis)
    //         String cookieHeader = String.format("%s=%s; HttpOnly; Path=/; Max-Age=%d; SameSite=None; Secure=%b",
    //                 "jwtToken", jwt, 10 * 60, false);
    //         response.addHeader("Set-Cookie", cookieHeader);

    //         // 📝 3. Retour du Token dans la Réponse JSON pour Local Storage
    //         Map<String, String> responseBody = new HashMap<>();
    //         responseBody.put("token", jwt);
    //         responseBody.put("message", "Connexion réussie");

    //         // 📜 Log pour vérifier le JWT généré
    //         System.out.println("🔑 JWT généré : " + jwt);

    //         // ✅ Stocker le token en Cookie sécurisé
    //         // jwtUtils.setTokenInCookie(jwt, response);

    //         // ✅ Stocker le token pour Swagger ou le Frontend
    //         // Map<String, String> tokenMap = new HashMap<>();
    //         // tokenMap.put("token", jwt);

    //         // ✅ Réponse avec le Token pour Local Storage et Cookie
    //         return ResponseEntity.ok(responseBody);

    //     } catch (Exception e) {
    //         // ❌ En cas d'erreur d'authentification
    //         Map<String, String> errorResponse = new HashMap<>();
    //         errorResponse.put("message", "Échec de la connexion");
    //         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    //     }
    // }

    @PostMapping("/login3")
    public ResponseEntity<Map<String, String>> authenticateUserWithCookieAndLocalStorage(
            @RequestBody LoginRequest loginRequest,
            HttpServletResponse response
    ) {
        try {
            // 🔐 Authentification
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // ✅ Récupération de `userId` depuis la base de données
            User user = userService.findByEmail(loginRequest.getEmail())
                        .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec l'email: " + loginRequest.getEmail()));

            UUID userId = UUID.fromString(user.getId());

            // 🔑 Génération du Token JWT avec `userId`
            String jwt = jwtUtils.generateJwtToken(authentication, userId);

            // 🍪 1. Stockage dans un Cookie sécurisé
            Cookie jwtCookie = new Cookie("jwtToken", jwt);
            jwtCookie.setHttpOnly(true);  // HttpOnly pour éviter accès JavaScript (protection XSS)
            jwtCookie.setSecure(false);   // ⚠️ `true` en production (HTTPS requis)
            jwtCookie.setPath("/");
            jwtCookie.setMaxAge(10 * 60); // Expiration : 10 minutes
            response.addCookie(jwtCookie);

            // 🔥 2. Configuration SameSite pour le CORS (utiliser "None" en prod)
            String cookieHeader = String.format("%s=%s; HttpOnly; Path=/; Max-Age=%d; SameSite=None; Secure=%b",
                    "jwtToken", jwt, 10 * 60, false);
            response.addHeader("Set-Cookie", cookieHeader);

            // 📝 3. Retour du Token dans la Réponse JSON pour Local Storage
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("token", jwt);
            responseBody.put("message", "Connexion réussie");

            // 📜 Log du JWT généré
            System.out.println("🔑 JWT généré : " + jwt);

            return ResponseEntity.ok(responseBody);

        } catch (Exception e) {
            // ❌ En cas d'erreur d'authentification
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Échec de la connexion");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }


    // @PostMapping("/login4")
    // public ResponseEntity<Map<String, String>> authenticateUser(@RequestParam String email, @RequestParam String password, HttpServletResponse response) {
    //     Authentication authentication = authenticationManager.authenticate(
    //             new UsernamePasswordAuthenticationToken(email, password)
    //     );
    //     SecurityContextHolder.getContext().setAuthentication(authentication);

    //     // 🔑 Génération du Token JWT
    //     String jwt = jwtUtils.generateJwtToken(authentication);

    //     // 🔥 Stockage dans un Cookie sécurisé
    //     Cookie jwtCookie = new Cookie(JWT_COOKIE_NAME, jwt);
    //     jwtCookie.setHttpOnly(true);  // HttpOnly pour empêcher l'accès en JavaScript
    //     jwtCookie.setSecure(false);   // ⚠️ Utilisez 'true' en production (HTTPS requis)
    //     jwtCookie.setPath("/");
    //     jwtCookie.setMaxAge(10 * 60); // Expire dans 10 minutes
    //     response.addCookie(jwtCookie);

    //     // ✅ Retour du Token dans le Body JSON pour Local Storage
    //     Map<String, String> responseBody = new HashMap<>();
    //     responseBody.put("token", jwt);
    //     responseBody.put("message", "Connexion réussie");

    //     return ResponseEntity.ok(responseBody);
    // }







    // @PostMapping("/login2")
    // public ResponseEntity<Map<String, String>> authenticateUser(@RequestBody Map<String, String> request) {
    //     String email = request.get("email");
    //     String password = request.get("password");

    //     Authentication authentication = authenticationManager.authenticate(
    //             new UsernamePasswordAuthenticationToken(email, password)
    //     );

    //     SecurityContextHolder.getContext().setAuthentication(authentication);
    //     String jwt = jwtUtils.generateJwtToken(authentication);

    //     return ResponseEntity.ok(Map.of("token", jwt, "message", "Connexion réussie !"));
    // }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        user.setId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    // @GetMapping("/me")
    // public ResponseEntity<Map<String, String>> getUserInfo(@CookieValue(name = "jwtToken", required = false) String token) {
    //     if (token == null || !jwtUtils.validateJwtToken(token)) {
    //         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Utilisateur non authentifié"));
    //     }

    //     String email = jwtUtils.getUserFromJwtToken(token);

    //     // ✅ Création d'une réponse sous forme de Map
    //     Map<String, String> response = new HashMap<>();
    //     response.put("email", email);
    //     response.put("message", "Utilisateur authentifié");

    //     return ResponseEntity.ok(response);
    // }

    @GetMapping("/me")
    public ResponseEntity<Map<String, String>> getUserInfo(
            @CookieValue(name = "jwtToken", required = false) String token) {

        if (token == null || !jwtUtils.validateJwtToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                .body(Map.of("error", "Utilisateur non authentifié"));
        }

        String email = jwtUtils.getUserFromJwtToken(token);

        Map<String, String> response = new HashMap<>();
        response.put("email", email);
        response.put("message", "Utilisateur authentifié");

        return ResponseEntity.ok(response);
    }


    // @PostMapping("/login")
    // public ResponseEntity<Map<String, String>> authenticateUser(
    //         @RequestBody Map<String, String> request,
    //         HttpServletResponse response) {

    //     String email = request.get("email");
    //     String password = request.get("password");

    //     Authentication authentication = authenticationManager.authenticate(
    //             new UsernamePasswordAuthenticationToken(email, password)
    //     );

    //     SecurityContextHolder.getContext().setAuthentication(authentication);
    //     String jwt = jwtUtils.generateJwtToken(authentication);

    //     // 🔥 Création du cookie sécurisé
    //     Cookie jwtCookie = new Cookie("jwtToken", jwt);
    //     jwtCookie.setHttpOnly(true); // ❌ Empêche l'accès via JS
    //     jwtCookie.setSecure(false);   // 🔒 Seulement en HTTPS
    //     jwtCookie.setPath("/");      // 🌍 Disponible pour toute l’API
    //     jwtCookie.setMaxAge(24 * 60 * 60); // ⏳ Expire en 1 jour

    //     response.addCookie(jwtCookie);

    //     return ResponseEntity.ok(Map.of("message", "Connexion réussie !"));
    // }

    // @PostMapping("/logout")
    // public ResponseEntity<String> logout(HttpServletResponse response) {
    //     Cookie jwtCookie = new Cookie("jwtToken", null);
    //     jwtCookie.setHttpOnly(true);
    //     jwtCookie.setSecure(false);
    //     jwtCookie.setPath("/");
    //     jwtCookie.setMaxAge(0); // ❌ Expire immédiatement

    //     response.addCookie(jwtCookie);
    //     return ResponseEntity.ok("Déconnexion réussie !");
    // }

    @GetMapping("/me/firstname")
    public ResponseEntity<?> getFirstName(@CookieValue(name = "jwtToken", required = false) String token) {
        // Vérifie la présence et la validité du token
        if (token == null || !jwtUtils.validateJwtToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utilisateur non authentifié");
        }

        // Récupère l'email de l'utilisateur depuis le token
        String email = jwtUtils.getUserFromJwtToken(token);

        // Cherche l'utilisateur dans la base de données
        // User user = userService.findByEmail(email);
        User user = userService.findByEmail(email)
                       .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur introuvable");
        }

        // Renvoie le prénom de l'utilisateur
        Map<String, String> response = new HashMap<>();
        response.put("firstname", user.getFirstName());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logoutWithCookie(HttpServletResponse response) {
        // 🔐 Invalidation du SecurityContext
        SecurityContextHolder.clearContext();

        // 🍪 Supprimer le Cookie JWT
        Cookie jwtCookie = new Cookie(JWT_COOKIE_NAME, null);
        jwtCookie.setHttpOnly(false);
        jwtCookie.setSecure(false); // 🔒 À mettre à true en production
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(0); // ❌ Expire immédiatement

        // 🔥 Configuration SameSite pour le CORS
        // 🚨 Utilisez "None" en production (HTTPS requis)
        jwtCookie.setDomain("localhost"); // Correspond au domaine du frontend
        response.addHeader("Set-Cookie", String.format("%s=%s; HttpOnly; Path=/; Max-Age=0; SameSite=None; Secure=%b",
                JWT_COOKIE_NAME, "", false)); // ⚠️ false pour HTTP en local, true en HTTPS

        response.addCookie(jwtCookie);

        // ✅ Réponse de succès
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", "Déconnexion réussie");
        return ResponseEntity.ok(responseBody);
    }


    // @PostMapping("/login-swagger")
    // public ResponseEntity<Map<String, String>> authenticateUserForSwagger(
    //         @RequestBody LoginRequest loginRequest
    // ) {
    //     try {
    //         // 🔐 Authentification
    //         Authentication authentication = authenticationManager.authenticate(
    //                 new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
    //         );
    //         SecurityContextHolder.getContext().setAuthentication(authentication);

    //         // 🔑 Génération du Token JWT
    //         String jwt = jwtUtils.generateJwtToken(authentication);

    //         // 📝 Retour du Token dans la Réponse JSON pour Local Storage
    //         Map<String, String> responseBody = new HashMap<>();
    //         responseBody.put("token", jwt);
    //         responseBody.put("message", "Connexion réussie");

    //         // 📜 Log pour vérifier le JWT généré
    //         System.out.println("🔑 JWT généré pour Swagger : " + jwt);

    //         // ✅ Réponse avec le Token pour Local Storage
    //         return ResponseEntity.ok(responseBody);

    //     } catch (Exception e) {
    //         // ❌ En cas d'erreur d'authentification
    //         Map<String, String> errorResponse = new HashMap<>();
    //         errorResponse.put("message", "Échec de la connexion");
    //         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    //     }
    // }

    // @PostMapping("/login-swagger")
    // public ResponseEntity<Map<String, String>> authenticateUserForSwagger(
    //         @RequestBody LoginRequest loginRequest,
    //         HttpServletResponse response
    // ) {
    //     try {
    //         // 🔐 Authentification
    //         Authentication authentication = authenticationManager.authenticate(
    //                 new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
    //         );
    //         SecurityContextHolder.getContext().setAuthentication(authentication);

    //         // 🔑 Génération du Token JWT
    //         String jwt = jwtUtils.generateJwtToken(authentication);

    //         // 🍪 Stockage du JWT dans un Cookie sécurisé
    //         Cookie jwtCookie = new Cookie("auth_token", jwt);
    //         jwtCookie.setHttpOnly(false);  // HttpOnly pour empêcher l'accès en JavaScript
    //         jwtCookie.setSecure(false);   // ⚠️ Utilisez 'true' en production (HTTPS requis)
    //         jwtCookie.setPath("/");
    //         jwtCookie.setMaxAge(10 * 60); // Expiration : 10 minutes
    //         jwtCookie.setDomain("localhost"); // Correspond au domaine du frontend
    //         jwtCookie.setAttribute("SameSite", "None"); // SameSite=None pour Swagger
    //         response.addCookie(jwtCookie);

    //         // 📝 Retour du Token dans la Réponse JSON pour Local Storage
    //         Map<String, String> responseBody = new HashMap<>();
    //         responseBody.put("token", jwt);
    //         responseBody.put("message", "Connexion réussie");

    //         // 📜 Log pour vérifier le JWT généré
    //         System.out.println("🔑 JWT généré pour Swagger : " + jwt);

    //         // ✅ Réponse avec le Token pour Local Storage
    //         return ResponseEntity.ok(responseBody);

    //     } catch (Exception e) {
    //         // ❌ En cas d'erreur d'authentification
    //         Map<String, String> errorResponse = new HashMap<>();
    //         errorResponse.put("message", "Échec de la connexion");
    //         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    //     }
    // }

    // @PostMapping("/login-swagger")
    // public ResponseEntity<Map<String, String>> authenticateUserForSwagger(
    //         @RequestBody LoginRequest loginRequest,
    //         HttpServletResponse response
    // ) {
    //     try {
    //         Authentication authentication = authenticationManager.authenticate(
    //                 new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
    //         );
    //         SecurityContextHolder.getContext().setAuthentication(authentication);

    //         String jwt = jwtUtils.generateJwtToken(authentication);

    //         // 🍪 Stockage du JWT dans un Cookie sécurisé
    //         Cookie jwtCookie = new Cookie("jwtToken", jwt); // ✅ Utilisation de jwtToken
    //         jwtCookie.setHttpOnly(false);
    //         jwtCookie.setSecure(false);   // ⚠️ Utilisez 'true' en production (HTTPS requis)
    //         jwtCookie.setPath("/");
    //         jwtCookie.setMaxAge(10 * 60); // Expiration : 10 minutes
    //         jwtCookie.setDomain("localhost");
    //         jwtCookie.setAttribute("SameSite", "None");
    //         response.addCookie(jwtCookie);

    //         Map<String, String> responseBody = new HashMap<>();
    //         responseBody.put("token", jwt);
    //         responseBody.put("message", "Connexion réussie");

    //         return ResponseEntity.ok(responseBody);

    //     } catch (Exception e) {
    //         Map<String, String> errorResponse = new HashMap<>();
    //         errorResponse.put("message", "Échec de la connexion");
    //         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    //     }
    @PostMapping("/login-swagger")
    public ResponseEntity<Map<String, String>> authenticateUserForSwagger(
            @RequestBody LoginRequest loginRequest,
            HttpServletResponse response
    ) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // ✅ Récupération de `userId`
            User user = userService.findByEmail(loginRequest.getEmail())
                        .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec l'email: " + loginRequest.getEmail()));
            UUID userId = UUID.fromString(user.getId());

            // ✅ Génération du Token avec `userId`
            String jwt = jwtUtils.generateJwtToken(authentication, userId);

            Cookie jwtCookie = new Cookie("jwtToken", jwt);
            jwtCookie.setHttpOnly(false);
            jwtCookie.setSecure(false);
            jwtCookie.setPath("/");
            jwtCookie.setMaxAge(10 * 60);
            jwtCookie.setDomain("localhost");
            jwtCookie.setAttribute("SameSite", "None");
            response.addCookie(jwtCookie);

            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("token", jwt);
            responseBody.put("message", "Connexion réussie");

            return ResponseEntity.ok(responseBody);

        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Échec de la connexion");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }








}

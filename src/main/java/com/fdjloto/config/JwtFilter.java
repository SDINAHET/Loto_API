package com.fdjloto.config;

import com.fdjloto.api.utils.JwtUtil;
import com.fdjloto.api.model.Player;
import com.fdjloto.api.repository.PlayerRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    private final PlayerRepository playerRepository;
    private final JwtUtil jwtUtil;

    public JwtFilter(PlayerRepository playerRepository, JwtUtil jwtUtil) {
        this.playerRepository = playerRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            logger.warn("🔍 Aucun token ou format incorrect dans l'en-tête Authorization");
            chain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        try {
            // ✅ Valider le token et récupérer l'email et le rôle
            String email = jwtUtil.validateToken(token);
            String role = jwtUtil.getRoleFromToken(token);

            if (email == null || role == null) {
                logger.error("❌ Token invalide : email ou rôle manquant");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("Token invalide ou rôle manquant");
                return;
            }

            // 🔍 Récupérer le joueur par son email
            Optional<Player> playerOptional = playerRepository.findByEmail(email);
            if (playerOptional.isPresent()) {
                Player player = playerOptional.get();

                // 🛠️ Création d'un UserDetails avec le bon rôle
                UserDetails userDetails = User.withUsername(player.getEmail())
                        .password(player.getPassword())
                        .authorities(role) // 🔥 Utilisation du rôle extrait du token
                        .build();

                // 🔑 Définir l'authentification dans le contexte
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
                logger.info("✅ Authentification réussie pour l'email : {} avec le rôle : {}", email, role);
            } else {
                logger.warn("⚠️ Utilisateur introuvable pour l'email : {}", email);
            }

        } catch (Exception e) {
            logger.error("🚨 Erreur dans le filtre JWT : {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Token invalide : " + e.getMessage());
            return;
        }

        chain.doFilter(request, response);
    }
}

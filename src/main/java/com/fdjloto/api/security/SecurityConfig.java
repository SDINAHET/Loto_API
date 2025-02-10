package com.fdjloto.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     http.csrf(csrf -> csrf.disable())
    //         .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    //         .authorizeHttpRequests(auth -> auth
    //             .requestMatchers("/api/auth/**", "/swagger-ui/**", "/v3/api-docs/**", "/api/hello").permitAll()
    //             .anyRequest().authenticated()
    //         )
    //         .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    //         .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    //     return http.build();
    // }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // 🔴 Désactive CSRF pour les APIs REST stateless
                // .cors(cors -> cors.disable()) // 🔴 Désactive CORS (ajoute une config si nécessaire)
                // .httpBasic(httpBasic -> httpBasic.disable()) // 🔴 Désactive l'authentification basique
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 🔴 JWT = stateless
                .authorizeHttpRequests(auth -> auth
                        // Swagger UI accessible sans authentification
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll() // ✅ Swagger accessible sans JWT
                        // Auth API accessible sans authentification
                        .requestMatchers("/api/auth/**", "/api/hello", "/localhost:5500/**", "api/loto/scrape").permitAll()
                        // Endpoints protégés par JWT
                        .requestMatchers("/api/protected/**").permitAll()
                        .requestMatchers("/api/user/**").authenticated()  // Protégé par JWT
                        // .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 🔴 JWT = stateless
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)  // 🔐 Ajoute le filtre JWT
                .build();
    }
}


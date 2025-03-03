package com.fdjloto.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserDetailsService userDetailsService;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, UserDetailsService userDetailsService) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.userDetailsService = userDetailsService;
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
    // public GrantedAuthorityDefaults grantedAuthorityDefaults() {
    //     return new GrantedAuthorityDefaults(""); // Enlève le préfixe ROLE_
    // }


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
                // .headers(headers -> headers
                //     .frameOptions(frame -> frame.sameOrigin()) // ✅ Autoriser les iframes depuis la même origine
                //     .xssProtection(xss -> xss.disable()) // ✅ Désactiver la protection XSS si nécessaire
                // )
                .csrf(csrf -> csrf.disable()) // 🔴 Désactive CSRF pour les APIs REST stateless
                // .csrf(AbstractHttpConfigurer::disable) // ✅ Version optimisée
                // .anonymous(anonymous -> anonymous.disable()) // Supprime l'authentification anonyme
                .cors(cors -> cors.disable()) // 🔴 Désactive CORS (ajoute une config si nécessaire)
                // .httpBasic(httpBasic -> httpBasic.disable()) // 🔴 Désactive l'authentification basique
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 🔴 JWT = stateless
                .authorizeHttpRequests(auth -> auth
                        // Swagger UI accessible sans authentification
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/v1/api-docs/**", "/swagger-ui.html", "/login-swagger").permitAll() // ✅ Swagger accessible sans JWT
                        // .requestMatchers("/api/health").permitAll()
                        // Auth API accessible sans authentification
                        .requestMatchers("/api/auth/**", "/api/hello", "/localhost:5500/**", "api/loto/scrape").permitAll()
                        // Endpoints protégés par JWT
                        // .requestMatchers("/api/protected/**").permitAll()
                        // .requestMatchers("/api/tickets/**").authenticated()
                        .requestMatchers("/api/tickets/**", "/api/tickets", "/api/tickets/{ticketId}").hasAnyRole("USER", "ADMIN") // 🔐 Accès USER et ADMIN
                        .requestMatchers("/api/historique/last20").permitAll()
                        .requestMatchers("/api/predictions/generate", "/api/generate", "api/predictions/latest").permitAll()
                        .requestMatchers("/api/historique/last20/Detail/**").permitAll()
                        .requestMatchers("/api/tirages", "/api/tirages/**").permitAll()
                        // .requestMatchers("/api/users/**", "/api/users").authenticated()  // Protégé par JWT
                        // .requestMatchers("/api/users/**").hasRole("ADMIN")
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")  // 🔐 Accès ADMIN
                        .requestMatchers("/api/users/**").hasAnyRole("USER", "ADMIN") // 🔐 Accès USER et ADMIN
                        // .requestMatchers("/api/users/**").hasRole("ADMIN")  // 🔐 Accès ADMIN
                        // .requestMatchers("/api/users/**").permitAll()
                        // .requestMatchers("/api/tickets/**").permitAll()
                        // .requestMatchers("/api/users/**").hasAuthority("ROLE_ADMIN")
                        // .requestMatchers("/api/user/**").hasAnyRole("USER", "ADMIN") // 🔐 Accès USER et ADMIN
                        // .requestMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN")
                        // // 🔐 Accès USER et ADMIN
                        // .requestMatchers("/api/user/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                        // .requestMatchers("/api/protected/userinfo").hasAuthority("SCOPE_user") // Vérifie si l'utilisateur a le bon scope
                        // .requestMatchers("/api/user/**").authenticated()  // Protégé par JWT
                        .requestMatchers("/api/protected/**").authenticated()  // Protégé par JWT
                        .anyRequest().authenticated()
                )
                // .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())));
                // .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 🔴 JWT = stateless
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)  // 🔐 Ajoute le filtre JWT
                .build();
    }

    // @Bean
    // public UserDetailsService userDetailsService() {
    //     return email -> userDetailsService.loadUserByUsername(email);
    // }

    // @Bean
    // public JwtAuthenticationConverter jwtAuthenticationConverter() {
    //     return new JwtAuthenticationConverter();
    // }
//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         return http
//                 .csrf(AbstractHttpConfigurer::disable) // ✅ Désactive CSRF pour API stateless
//                 .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 🔴 JWT = stateless
//                 .authorizeHttpRequests(auth -> auth
//                         .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll() // ✅ Swagger accessible sans JWT
//                         .requestMatchers("/api/auth/**").permitAll() // 🔓 Auth accessible sans token
//                         .requestMatchers("/api/protected/**").authenticated() // 🔐 JWT obligatoire
//                 )
//                 .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)  // 🔐 Ajoute le filtre JWT
//                 .build();
//     }
}

// package com.fdjloto.api.security;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     private final JwtAuthenticationFilter jwtAuthenticationFilter;

//     public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
//         this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//     }

//     @Bean
//     public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//         return authenticationConfiguration.getAuthenticationManager();
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         return http
//                 .csrf(csrf -> csrf.disable()) // ✅ Désactive CSRF pour API REST
//                 .cors(cors -> cors.disable()) // 🔴 Désactive CORS (peut être remplacé par une config spécifique)
//                 .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // ✅ JWT = Stateless
//                 .authorizeHttpRequests(auth -> auth
//                         // Endpoints publics (sans authentification)
//                         .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
//                         .requestMatchers("/api/auth/**", "/api/hello", "/localhost:5500/**", "/api/loto/scrape").permitAll()

//                         // Routes accessibles aux utilisateurs connectés
//                         .requestMatchers("/api/users/**").authenticated()  // 🔐 JWT obligatoire pour users
//                         .requestMatchers("/api/protected/**").authenticated()  // 🔐 JWT obligatoire

//                         // Routes spécifiques aux admins
//                         // .requestMatchers("/api/admin/**").hasRole("ADMIN")  // 🔐 Accessible uniquement aux admins
//                 )
//                 .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)  // 🔐 Ajout du filtre JWT
//                 // .oauth2Login() // ✅ Ajout OAuth2 Login si besoin
//                 .build();
//     }
// }


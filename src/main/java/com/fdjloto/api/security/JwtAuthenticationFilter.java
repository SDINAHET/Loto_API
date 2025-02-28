// package com.fdjloto.api.security;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;
// import java.io.IOException;

// @Component
// public class JwtAuthenticationFilter extends OncePerRequestFilter {

//     private final JwtUtils jwtUtils;
//     private final UserDetailsService userDetailsService;

//     public JwtAuthenticationFilter(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
//         this.jwtUtils = jwtUtils;
//         this.userDetailsService = userDetailsService;
//     }

//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//             throws ServletException, IOException {
//         String token = parseJwt(request);

//         if (token != null && jwtUtils.validateJwtToken(token)) {
//             String username = jwtUtils.getUserFromJwtToken(token);
//             UserDetails userDetails = userDetailsService.loadUserByUsername(username);

//             UsernamePasswordAuthenticationToken authentication =
//                     new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//             SecurityContextHolder.getContext().setAuthentication(authentication);
//         }

//         filterChain.doFilter(request, response);
//     }

//     private String parseJwt(HttpServletRequest request) {
//         String headerAuth = request.getHeader("Authorization");
//         if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
//             return headerAuth.substring(7);
//         }
//         return null;
//     }
// }

// // package com.fdjloto.api.security;

// // import jakarta.servlet.FilterChain;
// // import jakarta.servlet.ServletException;
// // import jakarta.servlet.http.Cookie;
// // import jakarta.servlet.http.HttpServletRequest;
// // import jakarta.servlet.http.HttpServletResponse;
// // import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// // import org.springframework.security.core.context.SecurityContextHolder;
// // import org.springframework.security.core.userdetails.UserDetails;
// // import org.springframework.security.core.userdetails.UserDetailsService;
// // import org.springframework.stereotype.Component;
// // import org.springframework.web.filter.OncePerRequestFilter;

// // import java.io.IOException;
// // import java.util.Arrays;

// // @Component
// // public class JwtAuthenticationFilter extends OncePerRequestFilter {

// //     private final JwtUtils jwtUtils;
// //     private final UserDetailsService userDetailsService;

// //     public JwtAuthenticationFilter(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
// //         this.jwtUtils = jwtUtils;
// //         this.userDetailsService = userDetailsService;
// //     }

// //     @Override
// //     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
// //             throws ServletException, IOException {

// //         // ✅ Recherche du JWT dans les cookies
// //         String token = Arrays.stream(request.getCookies())
// //                 .filter(cookie -> "jwtToken".equals(cookie.getName()))
// //                 .map(Cookie::getValue)
// //                 .findFirst()
// //                 .orElse(null);

// //         // ✅ Si un token est trouvé, on le valide
// //         if (token != null && jwtUtils.validateJwtToken(token)) {
// //             String username = jwtUtils.getUserFromJwtToken(token);
// //             UserDetails userDetails = userDetailsService.loadUserByUsername(username);

// //             UsernamePasswordAuthenticationToken authentication =
// //                     new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
// //             SecurityContextHolder.getContext().setAuthentication(authentication);
// //         }

// //         filterChain.doFilter(request, response);
// //     }
// // }

// // package com.fdjloto.api.security;

// // import jakarta.servlet.FilterChain;
// // import jakarta.servlet.ServletException;
// // import jakarta.servlet.http.Cookie;
// // import jakarta.servlet.http.HttpServletRequest;
// // import jakarta.servlet.http.HttpServletResponse;
// // import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// // import org.springframework.security.core.context.SecurityContextHolder;
// // import org.springframework.security.core.userdetails.UserDetails;
// // import org.springframework.security.core.userdetails.UserDetailsService;
// // import org.springframework.stereotype.Component;
// // import org.springframework.web.filter.OncePerRequestFilter;

// // import java.io.IOException;
// // import java.util.Arrays;
// // import java.util.List;

// // @Component
// // public class JwtAuthenticationFilter extends OncePerRequestFilter {

// //     private final JwtUtils jwtUtils;
// //     private final UserDetailsService userDetailsService;

// //     public JwtAuthenticationFilter(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
// //         this.jwtUtils = jwtUtils;
// //         this.userDetailsService = userDetailsService;
// //     }

// //     // ✅ Liste des URLs publiques (pas besoin de JWT pour y accéder)
// //     private static final List<String> PUBLIC_URLS = Arrays.asList(
// //             "/api/historique/last20",
// //             "/api/predictions/generate",
// //             "/api/generate",
// //             "/api/predictions/latest",
// //             "/api/tirages",
// //             "/api/tirages/**",
// //             "/swagger-ui/**",
// //             "/v3/api-docs/**",
// //             "/api/auth/**"
// //     );

// //     @Override
// //     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
// //             throws ServletException, IOException {

// //         // ✅ Vérification si l'URL est publique
// //         String requestURI = request.getRequestURI();
// //         boolean isPublic = PUBLIC_URLS.stream().anyMatch(requestURI::startsWith);

// //         // ✅ Si l'URL est publique, on passe le filtre sans authentification
// //         if (isPublic) {
// //             filterChain.doFilter(request, response);
// //             return;
// //         }

// //         // ✅ Pour les URLs protégées, on cherche le JWT dans le cookie
// //         String token = Arrays.stream(request.getCookies())
// //                 .filter(cookie -> "jwtToken".equals(cookie.getName()))
// //                 .map(Cookie::getValue)
// //                 .findFirst()
// //                 .orElse(null);

// //         // ✅ Si un token est trouvé, on le valide
// //         if (token != null && jwtUtils.validateJwtToken(token)) {
// //             String username = jwtUtils.getUserFromJwtToken(token);
// //             UserDetails userDetails = userDetailsService.loadUserByUsername(username);

// //             UsernamePasswordAuthenticationToken authentication =
// //                     new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
// //             SecurityContextHolder.getContext().setAuthentication(authentication);
// //         }

// //         // ✅ Continue la chaîne des filtres
// //         filterChain.doFilter(request, response);
// //     }
// // }


// // package com.fdjloto.api.security;

// // import jakarta.servlet.FilterChain;
// // import jakarta.servlet.ServletException;
// // import jakarta.servlet.http.Cookie;
// // import jakarta.servlet.http.HttpServletRequest;
// // import jakarta.servlet.http.HttpServletResponse;
// // import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// // import org.springframework.security.core.context.SecurityContextHolder;
// // import org.springframework.security.core.userdetails.UserDetails;
// // import org.springframework.security.core.userdetails.UserDetailsService;
// // import org.springframework.stereotype.Component;
// // import org.springframework.web.filter.OncePerRequestFilter;

// // import java.io.IOException;
// // import java.util.Arrays;

// // @Component
// // public class JwtAuthenticationFilter extends OncePerRequestFilter {

// //     private final JwtUtils jwtUtils;
// //     private final UserDetailsService userDetailsService;

// //     public JwtAuthenticationFilter(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
// //         this.jwtUtils = jwtUtils;
// //         this.userDetailsService = userDetailsService;
// //     }

// //     @Override
// //     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
// //             throws ServletException, IOException {

// //         // ✅ Recherche du JWT uniquement dans les cookies
// //         String token = Arrays.stream(request.getCookies())
// //                 .filter(cookie -> "jwtToken".equals(cookie.getName()))
// //                 .map(Cookie::getValue)
// //                 .findFirst()
// //                 .orElse(null);

// //         // ✅ Si un token est trouvé, on le valide
// //         if (token != null && jwtUtils.validateJwtToken(token)) {
// //             String username = jwtUtils.getUserFromJwtToken(token);
// //             UserDetails userDetails = userDetailsService.loadUserByUsername(username);

// //             UsernamePasswordAuthenticationToken authentication =
// //                     new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
// //             SecurityContextHolder.getContext().setAuthentication(authentication);
// //         }

// //         // ✅ Continue la chaîne des filtres
// //         filterChain.doFilter(request, response);
// //     }
// // }

// package com.fdjloto.api.security;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import java.io.IOException;
// import java.util.List;
// import java.util.stream.Collectors;

// @Component
// public class JwtAuthenticationFilter extends OncePerRequestFilter {

//     private final JwtUtils jwtUtils;
//     private final UserDetailsService userDetailsService;

//     public JwtAuthenticationFilter(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
//         this.jwtUtils = jwtUtils;
//         this.userDetailsService = userDetailsService;
//     }

//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//             throws ServletException, IOException {
//         String token = parseJwt(request);

//         if (token != null && jwtUtils.validateJwtToken(token)) {
//             String username = jwtUtils.getUserFromJwtToken(token);

//             // 🔥 Extraction des rôles depuis le token JWT
//             List<String> roles = jwtUtils.getRolesFromJwtToken(token);

//             // 🔥 Conversion des rôles en `GrantedAuthority`
//             List<GrantedAuthority> authorities = roles.stream()
//                     .map(role -> new SimpleGrantedAuthority(role)) // Ne pas enlever le préfixe 'ROLE_'
//                     // .map(SimpleGrantedAuthority::new)
//                     .collect(Collectors.toList());

//             // 🔥 Création de l'objet `Authentication` avec les rôles
//             UsernamePasswordAuthenticationToken authentication =
//                     new UsernamePasswordAuthenticationToken(username, null, authorities);

//             // 🔥 Définition du contexte de sécurité Spring
//             SecurityContextHolder.getContext().setAuthentication(authentication);
//         }

//         filterChain.doFilter(request, response);
//     }

//     // 🔑 Extraction du token JWT depuis l'header Authorization
//     private String parseJwt(HttpServletRequest request) {
//         String headerAuth = request.getHeader("Authorization");
//         if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
//             return headerAuth.substring(7);
//         }
//         return null;
//     }

//     // 🔥 Debug des rôles extraits
//     System.out.println("Roles extraits du token : " + roles);
// }

// package com.fdjloto.api.security;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import java.io.IOException;
// import java.util.List;
// import java.util.stream.Collectors;

// @Component
// public class JwtAuthenticationFilter extends OncePerRequestFilter {

//     private final JwtUtils jwtUtils;
//     private final UserDetailsService userDetailsService;

//     public JwtAuthenticationFilter(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
//         this.jwtUtils = jwtUtils;
//         this.userDetailsService = userDetailsService;
//     }

//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//             throws ServletException, IOException {
//         String token = parseJwt(request);

//         if (token != null && jwtUtils.validateJwtToken(token)) {
//             String username = jwtUtils.getUserFromJwtToken(token);

//             // 🔥 Extraction des rôles depuis le token JWT
//             List<String> roles = jwtUtils.getRolesFromJwtToken(token);

//             // 🔥 Debug des rôles extraits (à cet endroit)
//             System.out.println("Roles extraits du token : " + roles);

//             // 🔥 Conversion des rôles en `GrantedAuthority`
//             List<GrantedAuthority> authorities = roles.stream()
//                     .map(role -> new SimpleGrantedAuthority(role)) // Ne pas enlever le préfixe 'ROLE_'
//                     .collect(Collectors.toList());

//             // 🔥 Création de l'objet `Authentication` avec les rôles
//             UsernamePasswordAuthenticationToken authentication =
//                     new UsernamePasswordAuthenticationToken(username, null, authorities);

//             // 🔥 Définition du contexte de sécurité Spring
//             SecurityContextHolder.getContext().setAuthentication(authentication);
//         }

//         filterChain.doFilter(request, response);
//     }

//     // 🔑 Extraction du token JWT depuis l'header Authorization
//     private String parseJwt(HttpServletRequest request) {
//         String headerAuth = request.getHeader("Authorization");
//         if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
//             return headerAuth.substring(7);
//         }
//         return null;
//     }
// }


package com.fdjloto.api.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 🔥 Recherche du JWT dans l'en-tête Authorization ou le cookie jwtToken
        String token = getTokenFromHeader(request).orElseGet(() -> getTokenFromCookie(request).orElse(null));

        // 🔥 Si un token est trouvé, on le valide
        if (token != null && jwtUtils.validateJwtToken(token)) {
            String username = jwtUtils.getUserFromJwtToken(token);

            // 🔥 Extraction des rôles depuis le token JWT
            List<String> roles = jwtUtils.getRolesFromJwtToken(token);

            // 🔥 Conversion des rôles en `GrantedAuthority`
            List<GrantedAuthority> authorities = roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            // 🔥 Création de l'objet `Authentication` avec les rôles
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);

            // 🔥 Définition du contexte de sécurité Spring
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    // 🔑 Recherche du token dans l'en-tête Authorization
    private Optional<String> getTokenFromHeader(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
            return Optional.of(headerAuth.substring(7));
        }
        return Optional.empty();
    }

    // 🔑 Recherche du token dans le cookie jwtToken
    private Optional<String> getTokenFromCookie(HttpServletRequest request) {
        if (request.getCookies() != null) {
            return Arrays.stream(request.getCookies())
                    .filter(cookie -> "jwtToken".equals(cookie.getName()))
                    .map(Cookie::getValue)
                    .findFirst();
        }
        return Optional.empty();
    }
}

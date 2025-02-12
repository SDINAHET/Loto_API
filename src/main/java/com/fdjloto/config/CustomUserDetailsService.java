// package com.fdjloto.config;

// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// import java.util.Collections;

// @Service
// public class CustomUserDetailsService implements UserDetailsService {

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         // Simule un utilisateur trouvé en base de données
//         if (!"admin".equals(username)) {
//             throw new UsernameNotFoundException("Utilisateur non trouvé : " + username);
//         }

//         return User.withUsername(username)
//                 .password(new BCryptPasswordEncoder().encode("password")) // Mot de passe crypté
//                 .authorities(Collections.emptyList()) // Rôles vides pour l'exemple
//                 .build();
//     }
// }

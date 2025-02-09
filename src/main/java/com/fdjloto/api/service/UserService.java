// package com.fdjloto.api.service;

// import com.fdjloto.api.model.User;
// import com.fdjloto.api.repository.UserRepository;

// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class UserService {
//     private final UserRepository userRepository;

//     public UserService(UserRepository userRepository) {
//         this.userRepository = userRepository;
//     }

//     public List<User> getAllUsers() {
//         return userRepository.findAll();
//     }

//     public Optional<User> getUserById(String id) {
//         return userRepository.findById(id);
//     }

//     public User saveUser(User user) {
//         return userRepository.save(user);
//     }

//     public User updateUser(String id, User updatedUser) {
//         User user = userRepository.findById(id)
//                 .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

//         user.setFirstName(updatedUser.getFirstName());
//         user.setLastName(updatedUser.getLastName());
//         user.setEmail(updatedUser.getEmail());
//         user.setPassword(updatedUser.getPassword());
//         user.setAdmin(updatedUser.isAdmin());

//         return userRepository.save(user);
//     }

//     public void deleteUser(String id) {
//         userRepository.deleteById(id);
//     }

//     @Override
//     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//         User user = userRepository.findByEmail(email)
//                 .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

//         return org.springframework.security.core.userdetails.User
//                 .withUsername(user.getEmail())
//                 .password(user.getPassword())
//                 .roles(user.isAdmin() ? "ADMIN" : "USER")
//                 .build();
//     }

// }
package com.fdjloto.api.service;

import com.fdjloto.api.model.User;
import com.fdjloto.api.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String id) { // ✅ Change Long -> String
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // 🔐 Hash du mot de passe
        return userRepository.save(user);
    }

    public User updateUser(String id, User updatedUser) { // ✅ Change Long -> String
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());
        user.setAdmin(updatedUser.isAdmin());

        return userRepository.save(user);
    }

    public void deleteUser(String id) { // ✅ Change Long -> String
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.isAdmin() ? "ADMIN" : "USER")
                .build();
    }
}

package com.fdjloto.api.service;

import com.fdjloto.api.model.User;
import com.fdjloto.api.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(UUID id) { // ✅ Garde UUID
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // @Override
    // public User updateUser(UUID id, User user) { // ✅ Garde UUID
    //     if (userRepository.existsById(id)) {
    //         user.setId(id.toString()); // ✅ Stocke UUID sous forme de String
    //         return userRepository.save(user);
    //     }
    //     return null;
    // }
    @Override
    public User updateUser(UUID id, User user) { // ✅ Garde UUID
        if (userRepository.existsById(id)) {
            user.setId(id); // ✅ Passe directement l'UUID
            return userRepository.save(user);
        }
        return null;
    }



    @Override
    public void deleteUser(UUID id) { // ✅ Garde UUID
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.isAdmin() ? "ADMIN" : "USER")
                .build();
    }
}

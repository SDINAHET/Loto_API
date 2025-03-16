package com.fdjloto.api.service;

import com.fdjloto.api.model.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(UUID id);  // ✅ Garde UUID
    Optional<User> getUserByEmail(String email);
    User createUser(User user);
    User updateUser(UUID id, User user); // ✅ Garde UUID
    void deleteUser(UUID id); // ✅ Garde UUID
    Optional<User> findByEmail(String email);
}


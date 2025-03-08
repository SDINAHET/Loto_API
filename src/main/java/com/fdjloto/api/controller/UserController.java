// package com.fdjloto.api.controller;

// import com.fdjloto.api.model.User;
// import com.fdjloto.api.service.UserService;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.responses.ApiResponse;
// import io.swagger.v3.oas.annotations.responses.ApiResponses;
// import io.swagger.v3.oas.annotations.security.SecurityRequirement;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;
// import java.util.Optional;
// import java.util.UUID;
// import jakarta.validation.Valid;

// // @CrossOrigin(origins = "http://127.0.0.1:5500") // 🔥 Autorise CORS pour Live Server
// @CrossOrigin(origins = "*")

// @RestController
// @RequestMapping("/api/users")
// @Tag(name = "User Management", description = "Endpoints for managing users")
// // @SecurityRequirement(name = "BearerAuth") // 🔐 Ajout de l'authentification JWT
// public class UserController {

//     private final UserService userService;
//     private final PasswordEncoder passwordEncoder;

//     public UserController(UserService userService, PasswordEncoder passwordEncoder) {
//         this.passwordEncoder = passwordEncoder;
//         this.userService = userService;
//     }

//     @Operation(summary = "Retrieve all users (Admin only)")
//     @ApiResponses(value = {
//         @ApiResponse(responseCode = "200", description = "List of users retrieved successfully"),
//         @ApiResponse(responseCode = "403", description = "Forbidden - Admin access required")
//     })
//     @GetMapping
//     @PreAuthorize("hasRole('ROLE_ADMIN')")
//     public ResponseEntity<List<User>> getAllUsers() {
//         return ResponseEntity.ok(userService.getAllUsers());
//     }

//     @Operation(summary = "Get user by ID")
//     @GetMapping("/{id}")
//     public ResponseEntity<User> getUserById(@PathVariable UUID id) {
//         Optional<User> user = userService.getUserById(id); // ✅ Utilise directement l'UUID
//         return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//     }

//     @Operation(summary = "Find user by email")
//     @GetMapping("/find")
//     public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
//         Optional<User> user = userService.getUserByEmail(email);
//         return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//     }

//     // @Operation(summary = "Create a new user")
//     // @PostMapping
//     // public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
//     //     user.setId(UUID.randomUUID()); // ✅ Génère un UUID natif
//     //     return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
//     // }
//     @Operation(summary = "Register a new user")
//     @PostMapping("/register")
//     public ResponseEntity<User> registerUser(@RequestBody User user) {
//         user.setPassword(passwordEncoder.encode(user.getPassword()));
//         return ResponseEntity.ok(userService.createUser(user));
//     }

//     @Operation(summary = "Update an existing user")
//     @PutMapping("/{id}")
//     public ResponseEntity<User> updateUser(@PathVariable UUID id, @Valid @RequestBody User user) {
//         return ResponseEntity.ok(userService.updateUser(id, user)); // ✅ Corrigé
//     }

//     @Operation(summary = "Delete a user (Admin only)")
//     @DeleteMapping("/{id}")
//     @PreAuthorize("hasRole('ROLE_ADMIN')")
//     public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
//         userService.deleteUser(id); // ✅ Utilise directement l'UUID
//         return ResponseEntity.noContent().build();
//     }
// }

package com.fdjloto.api.controller;

import com.fdjloto.api.model.User;
import com.fdjloto.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import jakarta.validation.Valid;
import java.util.Set;
import java.util.HashSet;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
@Tag(name = "User Management", description = "Endpoints for managing users")
@SecurityRequirement(name = "bearerAuth") // 🔐 Ajout de l'authentification JWT
@SecurityRequirement(name = "jwtCookieAuth") // 🔐 Ajout de l'authentification JWT via cookie

public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    // @Operation(summary = "Retrieve all users (Admin only)")
    // @ApiResponses(value = {
    //     @ApiResponse(responseCode = "200", description = "List of users retrieved successfully"),
    //     @ApiResponse(responseCode = "403", description = "Forbidden - Admin access required")
    // })
    // @GetMapping
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    // public ResponseEntity<List<User>> getAllUsers() {
    //     return ResponseEntity.ok(userService.getAllUsers());
    // }
    @Operation(summary = "Retrieve all users (Admin only)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of users retrieved successfully"),
        @ApiResponse(responseCode = "403", description = "Forbidden - Admin access required")
    })
    // @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Operation(summary = "Get user by ID (Admin and User)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User retrieved successfully"),
        @ApiResponse(responseCode = "403", description = "Forbidden - Admin access required"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // @Operation(summary = "Register a new user (Accessible to all)")
    // @ApiResponses(value = {
    //     @ApiResponse(responseCode = "201", description = "User registered successfully"),
    //     @ApiResponse(responseCode = "400", description = "Invalid user data")
    // })
    // @PostMapping("/register")
    // public ResponseEntity<User> registerUser(@RequestBody User user) {
    //     user.setId(UUID.randomUUID().toString());
    //     user.setPassword(passwordEncoder.encode(user.getPassword()));
    //     user.setAdmin(false); // ✅ Définit admin par défaut à false
    //     return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    // }

    @Operation(summary = "Register a new user (Accessible to all)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "User registered successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid user data")
    })
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        user.setId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // ✅ Par défaut, on assigne `admin = false`, donc rôle `USER`
        user.setAdmin(false);

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    @Operation(summary = "Update an existing user (Admin only)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid user data"),
        @ApiResponse(responseCode = "403", description = "Forbidden - Admin access required"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @Valid @RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @Operation(summary = "Delete a user (Admin only)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "User deleted successfully"),
        @ApiResponse(responseCode = "403", description = "Forbidden - Admin access required"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    // @PreAuthorize("hasRole('ADMIN')") // Utilisez 'ADMIN' sans le préfixe 'ROLE_'
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}

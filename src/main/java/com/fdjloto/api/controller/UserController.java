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

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import jakarta.validation.Valid;

/**
 * **Controller for managing user-related operations.**
 */
@CrossOrigin(
    origins = "http://127.0.0.1:5500", // 🔥 Enables CORS for Live Server
    allowCredentials = "true",
    allowedHeaders = "*",
    methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
)
@RestController
@RequestMapping("/api/users")
@Tag(name = "User Management", description = "Endpoints for managing users")
@SecurityRequirement(name = "bearerAuth") // 🔐 Adds JWT authentication
@SecurityRequirement(name = "jwtCookieAuth") // 🔐 Adds JWT authentication via cookies
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    /**
     * 🔹 Retrieves all users (Admin only)
     * - Only **Admins** can access this endpoint.
     * - Returns a list of all registered users.
     *
     * @return **200 OK** - List of users retrieved successfully.
     * @return **403 FORBIDDEN** - If a non-admin tries to access this endpoint.
     */
    @Operation(summary = "Retrieve all users (Admin only)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of users retrieved successfully"),
        @ApiResponse(responseCode = "403", description = "Forbidden - Admin access required")
    })
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')") // Restricts access to Admins only
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * 🔹 Retrieves a specific user by their ID.
     * - Admins can retrieve any user.
     * - Regular users can retrieve their own profile.
     *
     * @param id The unique identifier of the user.
     * @return **200 OK** - User retrieved successfully.
     * @return **403 FORBIDDEN** - If a non-admin tries to access another user's profile.
     * @return **404 NOT FOUND** - If the user does not exist.
     */
    @Operation(summary = "Get user by ID (Admin & User)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User retrieved successfully"),
        @ApiResponse(responseCode = "403", description = "Forbidden - Admin access required"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')") // Allows both Admins and Users
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * 🔹 Registers a new user.
     * - Accessible to anyone (public endpoint).
     * - Hashes the password before saving it in the database.
     * - By default, assigns the **USER** role.
     *
     * @param user The user object containing registration details.
     * @return **201 CREATED** - If the user is successfully registered.
     * @return **400 BAD REQUEST** - If the provided user data is invalid.
     */
    @Operation(summary = "Register a new user (Accessible to all)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "User registered successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid user data")
    })
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        user.setId(UUID.randomUUID().toString()); // Generate a unique UUID
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password
        user.setAdmin(false); // Default role is USER

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    /**
     * 🔹 Updates an existing user.
     * - Admins can update any user.
     * - Users can update their own profile.
     * - Encrypts the new password before saving.
     *
     * @param id   The unique identifier of the user.
     * @param user The updated user information.
     * @return **200 OK** - If the update is successful.
     * @return **400 BAD REQUEST** - If the provided data is invalid.
     * @return **403 FORBIDDEN** - If the user does not have permission to update this profile.
     * @return **404 NOT FOUND** - If the user does not exist.
     */
    @Operation(summary = "Update an existing user (Admin & User)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid user data"),
        @ApiResponse(responseCode = "403", description = "Forbidden - Admin/User access required"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')") // Allows both Admins and Users
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @Valid @RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    /**
     * 🔹 Deletes a user (Admin only).
     * - Admins can delete any user account.
     * - Regular users **cannot** delete accounts.
     *
     * @param id The unique identifier of the user to be deleted.
     * @return **204 NO CONTENT** - If the user is successfully deleted.
     * @return **403 FORBIDDEN** - If a non-admin tries to delete a user.
     * @return **404 NOT FOUND** - If the user does not exist.
     */
    @Operation(summary = "Delete a user (Admin only)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "User deleted successfully"),
        @ApiResponse(responseCode = "403", description = "Forbidden - Admin access required"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')") // Only Admins can delete users
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}

package com.fdjloto.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents a login request containing user credentials.
 * This DTO (Data Transfer Object) is used to capture login details from API requests.
 */
@Schema(description = "Login request containing user credentials.")
public class LoginRequest {

    @Schema(description = "User's email address", example = "user@example.com", required = true)
    private String email;   // User's email address

    @Schema(description = "User's password", example = "P@ssw0rd", required = true)
    private String password; // User's password

    /**
     * Default constructor required for deserialization.
     */
    public LoginRequest() {}

    /**
     * Parameterized constructor to initialize a login request with user credentials.
     *
     * @param email The user's email address
     * @param password The user's password
     */
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // ✅ Getters and Setters

    /**
     * Retrieves the user's email address.
     *
     * @return The email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address.
     *
     * @param email The email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the user's password.
     *
     * @return The password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     *
     * @param password The password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}

/**
 * 💡 Explanation:
 * - This class is a simple **DTO (Data Transfer Object)** for handling login requests.
 * - It contains **email and password fields** to authenticate a user.
 * - Includes a **default constructor** (necessary for frameworks like Spring Boot).
 * - Provides **getters and setters** for controlled access.
 */

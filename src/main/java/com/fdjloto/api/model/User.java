package com.fdjloto.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.UUID;

/**
 * Represents a user in the system.
 * This entity is mapped to the "users" table in the database.
 * Each user has attributes such as name, email, password, and admin status.
 */
@Schema(description = "Represents a user in the system")
@Entity
@Table(name = "users")
public class User {

    /** Unique identifier for the user stored as a String (UUID format) */
    @Id
    @Schema(description = "Unique identifier for the user", example = "123e4567-e89b-12d3-a456-426614174000")
    @Column(columnDefinition = "TEXT", unique = true, nullable = false)
    private String id;

    /** First name of the user, limited to 26 characters */
    @Size(max = 26, message = "First name cannot exceed 26 characters")
    @NotBlank(message = "First name is required")
    @Schema(description = "First name of the user", example = "John")
    private String firstName;

    /** Last name of the user, limited to 26 characters */
    @Size(max = 26, message = "Last name cannot exceed 26 characters")
    @NotBlank(message = "Last name is required")
    @Schema(description = "Last name of the user", example = "Doe")
    private String lastName;

    /** Email address of the user, must be unique and valid */
    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is required")
    @Column(nullable = false, unique = true)
    @Schema(description = "Email address of the user", example = "VZM0q@example.com")
    private String email;

    /** Password of the user, must be at least 6 characters */
    @Size(min = 6, message = "Password must contain at least 6 characters")
    @NotBlank(message = "Password is required")
    @Column(nullable = false)
    @Schema(description = "Password of the user", example = "P@ssw0rd")
    private String password;

    /** Defines if the user has admin privileges (default: false) */
    @Schema(defaultValue = "false", description = "Defines if the user has admin privileges")
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean admin;

    /** List of tickets associated with the user */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "List of tickets associated with the user")
    private List<Ticket> tickets;

    /**
     * Generates a UUID automatically before persisting the user into the database.
     * Ensures that a user always has a unique identifier.
     */
    @PrePersist
    public void generateUUID() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }

    /** Default constructor - Initializes admin as false */
    public User() {
        this.admin = false; // Ensures that new users are non-admin by default
    }

    /**
     * Parameterized constructor to create a new user.
     *
     * @param firstName  User's first name
     * @param lastName   User's last name
     * @param email      User's email address
     * @param password   User's password (must be encrypted before saving)
     * @param admin      Indicates if the user is an administrator
     */
    public User(String firstName, String lastName, String email, String password, boolean admin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.admin = admin;
    }

    /** Getters and Setters */
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isAdmin() { return admin; }
    public void setAdmin(boolean admin) { this.admin = admin; }

    public List<Ticket> getTickets() { return tickets; }
    public void setTickets(List<Ticket> tickets) { this.tickets = tickets; }

    /**
     * Returns the role of the user for security purposes.
     * @return "ROLE_ADMIN" if the user is an admin, otherwise "ROLE_USER"
     */
    public String getRole() {
        return this.admin ? "ROLE_ADMIN" : "ROLE_USER";
    }
}

/**
 * 💡 Explanation:
 * - This `User` class represents an entity stored in the **users** table.
 * - The `@PrePersist` method ensures that every user has a **unique UUID** before being saved.
 * - The **admin** field is **false by default** for security reasons.
 * - The `getRole()` method helps with **role-based authentication** in Spring Security.
 * - This class **ensures data validation** using annotations like `@Email`, `@Size`, and `@NotBlank`.
 */

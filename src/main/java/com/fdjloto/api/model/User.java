package com.fdjloto.api.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    private String id = UUID.randomUUID().toString();

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @Column(nullable = false)
    private boolean admin = false; // ✅ Valeur par défaut en Java

    public User() {
        this.admin = false; // ✅ Assure que tous les nouveaux utilisateurs sont non-admin
    }

    public User(String firstName, String lastName, String email, String password, boolean admin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.admin = admin; // ✅ Permet de définir la valeur lors de l'insertion
    }

    // ✅ Getters et Setters
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

    public boolean isAdmin() { return admin; } // ✅ Getter correct
    public void setAdmin(boolean admin) { this.admin = admin; } // ✅ Setter correct
}

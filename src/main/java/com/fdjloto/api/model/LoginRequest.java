package com.fdjloto.api.model;

public class LoginRequest {
    private String email;
    private String password;

    // Constructeur par défaut
    public LoginRequest() {}

    // Constructeur avec paramètres
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters et Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

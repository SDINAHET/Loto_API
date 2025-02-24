package com.fdjloto.api.payload;

public class MessageResponse {
    private String message;

    // Constructeur avec le message en paramètre
    public MessageResponse(String message) {
        this.message = message;
    }

    // Getter et Setter
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

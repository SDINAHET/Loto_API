package com.fdjloto.api.payload;

/**
 * Represents a standardized response message for API responses.
 * This class is commonly used to return simple text messages to the client.
 */
public class MessageResponse {

    /** The message content returned to the client */
    private String message;

    /**
     * Constructor that initializes the message response.
     *
     * @param message The message to be included in the response.
     */
    public MessageResponse(String message) {
        this.message = message;
    }

    /**
     * Retrieves the message content.
     *
     * @return The response message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Updates the message content.
     *
     * @param message The new message to be set.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}

/**
 * 💡 Explanation:
 * - This class is used to return **simple textual responses** from API endpoints.
 * - It follows a **standardized response format** for better API design.
 * - The **getter and setter** methods allow easy retrieval and modification of the message.
 */

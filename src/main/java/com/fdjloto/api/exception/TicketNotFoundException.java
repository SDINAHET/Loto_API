package com.fdjloto.api.exception;

/**
 * Custom exception thrown when a requested ticket is not found in the database.
 * This exception extends `RuntimeException`, allowing it to be used without explicit try-catch blocks.
 */
public class TicketNotFoundException extends RuntimeException {

    /**
     * Constructs a new `TicketNotFoundException` with a specific error message.
     *
     * @param message The detailed error message explaining why the exception was thrown.
     */
    public TicketNotFoundException(String message) {
        super(message); // Calls the parent RuntimeException constructor with the provided message
    }
}

/**
 * 💡 Explanation:
 * - This is a **custom exception** class that extends `RuntimeException`, making it an **unchecked exception**.
 * - It is used to **signal** that a ticket was not found in the database.
 * - The constructor accepts a **message parameter** to provide meaningful error descriptions.
 */

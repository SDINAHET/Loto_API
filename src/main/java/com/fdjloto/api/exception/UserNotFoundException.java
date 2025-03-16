package com.fdjloto.api.exception;

/**
 * Custom exception thrown when a requested user is not found in the database.
 * This exception extends `RuntimeException`, making it an unchecked exception.
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Constructs a new `UserNotFoundException` with a specific error message.
     *
     * @param message The error message explaining why the exception was thrown.
     */
    public UserNotFoundException(String message) {
        super(message); // Calls the parent RuntimeException constructor with the provided message
    }
}

/**
 * 💡 Explanation:
 * - This is a **custom exception** class used to indicate that a user was not found in the system.
 * - It extends `RuntimeException`, making it an **unchecked exception**, meaning it does not need to be explicitly handled with try-catch.
 * - The exception message provides additional context about the error.
 */

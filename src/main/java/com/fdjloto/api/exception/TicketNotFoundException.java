package com.fdjloto.api.exception;

/**
 * Exception personnalisée pour signaler qu'un ticket n'a pas été trouvé.
 */
public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(String message) {
        super(message);
    }
}

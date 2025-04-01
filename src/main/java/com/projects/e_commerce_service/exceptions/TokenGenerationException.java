package com.projects.e_commerce_service.exceptions;

public class TokenGenerationException extends Exception {
    public TokenGenerationException() {
        super("Error generating token.");
    }

    public TokenGenerationException(String message) {
        super(message);
    }
}

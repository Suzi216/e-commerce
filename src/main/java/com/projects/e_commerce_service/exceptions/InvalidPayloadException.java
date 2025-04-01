package com.projects.e_commerce_service.exceptions;

public class InvalidPayloadException extends Exception {

    public InvalidPayloadException() {
        super("Invalid request body");
    }

    public InvalidPayloadException(String message) {
        super(message);
    }
}

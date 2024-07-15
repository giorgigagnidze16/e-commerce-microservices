package com.ecom.microservice.api.exception;

/**
 * Exception thrown whenever resource is not found.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(Throwable t, String message) {
        super(message, t);
    }
}

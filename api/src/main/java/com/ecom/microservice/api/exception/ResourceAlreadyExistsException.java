package com.ecom.microservice.api.exception;


/**
 * Exception thrown whenever resource already exists.
 */
public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException(String message) {
        super(message);
    }

    public ResourceAlreadyExistsException(Throwable t, String message) {
        super(message, t);
    }
}

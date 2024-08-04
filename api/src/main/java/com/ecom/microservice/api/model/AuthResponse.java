package com.ecom.microservice.api.model;

/**
 * Authentication response dto
 * @param accessToken jwt token.
 */
public record AuthResponse(String accessToken) {
}

package com.ecom.microservice.api.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Authentication request dto
 *
 * @param email    of user account
 * @param password of user account
 */
public record AuthRequest(@NotNull @Email @Size(min = 3) String email,
                          @NotNull @Size(min = 8) String password) {
}

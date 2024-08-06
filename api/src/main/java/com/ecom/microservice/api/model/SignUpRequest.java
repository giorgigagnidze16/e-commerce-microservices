package com.ecom.microservice.api.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Sign up request dto.
 *
 * @param firstname of user
 * @param lastname  of user
 * @param email     of user
 * @param password  of account
 */
public record SignUpRequest(@NotNull @Size(min = 2, max = 50) String firstname,
                            @NotNull @Size(min = 2, max = 50) String lastname,
                            @NotNull @Email @Size(min = 3) String email,
                            @NotNull @Size(min = 8, max = 16) String password) {
}

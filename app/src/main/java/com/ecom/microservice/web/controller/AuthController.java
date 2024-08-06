package com.ecom.microservice.web.controller;

import com.ecom.microservice.api.model.AuthRequest;
import com.ecom.microservice.api.model.AuthResponse;
import com.ecom.microservice.api.model.Role;
import com.ecom.microservice.api.model.SignUpRequest;
import com.ecom.microservice.service.AuthService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "Auth")
@OpenAPIDefinition(tags = @Tag(name = "Auth", description = "Auth controller"))
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "Log in request", description = "Retrieve JWT token for authentication")
    @ApiResponses({
        @ApiResponse(responseCode = "403", description = "Incorrect credentials"),
        @ApiResponse(responseCode = "200", description = "Successful authentication, returns token")
    })
    @PostMapping("/login")
    ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest request) {
        log.debug("Received authentication request for {}", request.email());
        return authService.login(request);
    }

    @Operation(summary = "Sign up request", description = "Retrieve JWT token after successful registration")
    @ApiResponses({
        @ApiResponse(responseCode = "400", description = "Bad request, user already exists"),
        @ApiResponse(responseCode = "200", description = "Successful registration, returns token")
    })
    @PostMapping("/signup")
    ResponseEntity<AuthResponse> signUp(@RequestBody @Valid SignUpRequest request, @RequestParam Role role) {
        log.debug("Received signup request from {}", request.email());
        return authService.signUp(request, role);
    }
}

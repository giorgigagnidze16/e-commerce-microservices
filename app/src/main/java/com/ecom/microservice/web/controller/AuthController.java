package com.ecom.microservice.web.controller;

import com.ecom.microservice.api.model.AuthRequest;
import com.ecom.microservice.api.model.AuthResponse;
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
        return ResponseEntity.ofNullable(new AuthResponse(authService.login(request)));
    }
}

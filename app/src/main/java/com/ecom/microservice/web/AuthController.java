package com.ecom.microservice.web;

import com.ecom.microservice.api.model.AuthRequest;
import com.ecom.microservice.api.model.AuthResponse;
import com.ecom.microservice.service.AuthService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "Auth")
@OpenAPIDefinition(tags = @Tag(name = "Auth", description = "Auth controller"))
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ofNullable(new AuthResponse(authService.login(request)));
    }
}

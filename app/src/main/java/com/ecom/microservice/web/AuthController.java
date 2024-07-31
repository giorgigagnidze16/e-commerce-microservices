package com.ecom.microservice.web;

import com.ecom.microservice.api.model.AuthRequest;
import com.ecom.microservice.api.model.AuthResponse;
import com.ecom.microservice.entity.User;
import com.ecom.microservice.service.JwtService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Auth")
@OpenAPIDefinition(tags = @Tag(name = "Auth", description = "Auth controller"))
public class AuthController {
    private final JwtService jwtService;
    private final AuthenticationProvider authenticationProvider;

    @PostMapping("/login")
    ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        var authentication = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(
                request.email(),
                request.password()
        ));

        var user = (User) authentication.getPrincipal();
        var token = jwtService.generateToken(user.getUsername());

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, token)
                .body(new AuthResponse(token));
    }
}

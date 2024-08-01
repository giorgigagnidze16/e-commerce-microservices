package com.ecom.microservice.service;


import com.ecom.microservice.api.model.AuthRequest;
import com.ecom.microservice.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

/**
 * Service responsible for authentication.
 */
@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final AuthenticationProvider authenticationProvider;

    /**
     * Authenticates user with password credentials.
     *
     * @param request credentials
     * @return generated token
     */
    public String login(@NotNull AuthRequest request) {
        var authentication = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(
            request.email(),
            request.password()
        ));

        var user = (User) authentication.getPrincipal();

        return jwtService.generateToken(user.getUsername());
    }
}

package com.ecom.microservice.service;


import com.ecom.microservice.api.exception.ResourceAlreadyExistsException;
import com.ecom.microservice.api.exception.ResourceNotFoundException;
import com.ecom.microservice.api.model.AuthRequest;
import com.ecom.microservice.api.model.AuthResponse;
import com.ecom.microservice.api.model.Role;
import com.ecom.microservice.api.model.SignUpRequest;
import com.ecom.microservice.entity.RoleEntity;
import com.ecom.microservice.entity.User;
import com.ecom.microservice.repository.RoleRepository;
import com.ecom.microservice.repository.UserRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * Service responsible for authentication.
 */
@Service
@Validated
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final AuthenticationProvider authenticationProvider;

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    /**
     * Authenticates user with password credentials.
     *
     * @param request credentials
     * @return generated token
     */
    public ResponseEntity<AuthResponse> login(@NotNull AuthRequest request) {
        var authentication = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(
            request.email(),
            request.password()
        ));

        var user = (User) authentication.getPrincipal();

        var authResponse = new AuthResponse(jwtService.generateToken(user.getUsername()));

        return ResponseEntity.ofNullable(authResponse);
    }

    /**
     * Register user and save in the database.
     *
     * @param request user details.
     * @return access token
     */
    public ResponseEntity<AuthResponse> signUp(@NotNull SignUpRequest request, Role role) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new ResourceAlreadyExistsException("User already exists with email " + request.email());
        }

        RoleEntity roleEntity =
            roleRepository.findByName(role.name()).orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        String encodedPassword = encoder.encode(request.password());

        User user = User.builder()
            .email(request.email())
            .password(encodedPassword)
            .firstname(request.firstname())
            .lastname(request.lastname())
            .role(roleEntity)
            .build();

        userRepository.saveAndFlush(user);

        return login(new AuthRequest(request.email(), request.password()));
    }
}

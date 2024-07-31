package com.ecom.microservice.web;

import com.ecom.microservice.api.model.AuthRequest;
import com.ecom.microservice.api.model.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public class AuthController {

    @PostMapping("/login")
    ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ofNullable(null);
    }
}

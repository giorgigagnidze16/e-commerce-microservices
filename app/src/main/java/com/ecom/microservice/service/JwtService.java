package com.ecom.microservice.service;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

/**
 * Service for JWT Operations
 */
@Service
public class JwtService {

    private static final String SECRET = "4226452948404D635166546A576E5A7234753778214125432A462D4A614E6452";

    private final Key key;

    public JwtService() {
        this.key = Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    /**
     * Generate JWT token
     *
     * @param username of user
     * @return JWT Token
     */
    public String generateToken(String username) {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

    /**
     * Validates given token.
     *
     * @param token jwt string
     * @return boolean if token is valid or not
     */
    public boolean validate(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Gets username from token.
     *
     * @param token JWT token
     * @return user's email from token
     */
    public String getUsername(String token) {
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();
        return claims.getSubject();
    }
}

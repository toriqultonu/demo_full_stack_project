package com.autoindustry.backend.service;

import com.autoindustry.backend.dto.AuthRequest;
import com.autoindustry.backend.dto.AuthResponse;
import com.autoindustry.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class AuthService {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtEncoder jwtEncoder;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    @Value("${jwt.refresh-expiration}")
    private long refreshExpiration;

    public AuthResponse login(AuthRequest authRequest) {
        User user = userService.findByUsername(authRequest.getUsername());
        if (user != null && userService.passwordEncoder().matches(authRequest.getPassword(), user.getPassword())) {
            String token = generateToken(user, jwtExpiration);
            String refreshToken = generateToken(user, refreshExpiration);
            return new AuthResponse(token, refreshToken, user.getRole().name());
        }
        throw new RuntimeException("Invalid credentials");
    }

    public String refresh(String refreshToken) {
        // Validate refreshToken, extract user, generate new access token
        // For brevity, assuming validation logic here
        // Use JwtDecoder to decode and verify
        return generateToken(/* extracted user */, jwtExpiration);
    }

    private String generateToken(User user, long expiration) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(expiration, ChronoUnit.MILLIS))
                .subject(user.getUsername())
                .claim("role", user.getRole())
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
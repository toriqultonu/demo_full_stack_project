package com.autoindustry.backend.dto;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String refreshToken;
    private String role;

    public AuthResponse(String token, String refreshToken, String role) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.role = role;
    }
}
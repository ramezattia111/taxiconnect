package com.taxiconnect.authentication;

import com.taxiconnect.entities.roles.UserRoles;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private UserRoles Role;
    private String domaine;

    public AuthResponse(String token, UserRoles role, String domaine) {
        this.Role = role;
        this.token = token;
    }

    public AuthResponse(String token, UserRoles role) {
        this.Role = role;
        this.token = token;
    }
}

package com.taxiconnect.authentication;


import lombok.Getter;

// Classes pour les requêtes
@Getter
public class AuthRequest {
    private String email;
    private String motDePasse;
}

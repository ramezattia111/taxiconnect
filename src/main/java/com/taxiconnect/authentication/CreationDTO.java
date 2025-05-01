package com.taxiconnect.authentication;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import lombok.Getter;

import java.util.List;

import com.taxiconnect.entities.roles.UserRoles;

@Getter
public class CreationDTO {
    private String nom;
    private String prenom; // Ajouté pour correspondre à la page signup
    private String email;
    private String motDePasse;
    @Enumerated(EnumType.STRING) // Stocker les noms des rôles comme chaînes
    private UserRoles role; // Par exemple: "ADMIN", "RESPONSABLE_DOMAINE", "CHEF_PROJET", "MEMBRE_EQUIPE"

}

package com.taxiconnect.authentication;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.taxiconnect.entities.client.Client;
import com.taxiconnect.entities.roles.UserRoles;
import com.taxiconnect.entities.taxi.Taxi;
import com.taxiconnect.entities.user.User;
import com.taxiconnect.repo.UserRepo;
import com.taxiconnect.securite.JWTService;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTService jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo utilisateurRepository;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getMotDePasse())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Identifiants incorrects");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne : " + e.getMessage());
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);
        
        // Extract the role from authorities and convert to Role enum
        String roleString = userDetails.getAuthorities().iterator().next().getAuthority();
        // Remove the "ROLE_" prefix that Spring Security adds
        roleString = roleString.replace("ROLE_", "");
        UserRoles role = UserRoles.valueOf(roleString);
        
        // Get user domain based on role
        Optional<com.taxiconnect.entities.user.User> utilisateurOpt = utilisateurRepository.findByEmail(authRequest.getEmail());
        if (!utilisateurOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Utilisateur introuvable");
        }
        
        User utilisateur = utilisateurOpt.get();
        String domaine = null;
        
        
        
        return ResponseEntity.ok(new AuthResponse(jwt, role));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest request) {
        // Vérifier si l'email existe déjà
        if (utilisateurRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email déjà utilisé");
        }

        // Validation minimale de l’email
        if (request.getEmail() == null || !request.getEmail().contains("@")) {
            return ResponseEntity.badRequest().body("Format d'email invalide");
        }

        // Vérifier le mot de passe (au moins 8 caractères)
        if (request.getMotDePasse() == null || request.getMotDePasse().length() < 8) {
            return ResponseEntity.badRequest().body("Le mot de passe doit contenir au moins 8 caractères");
        }

        // Vérifier le domaine
      

        // Interdire la création d'un administrateur via signup
        if ("ADMINISTRATEUR".equals(request.getRole())) {
            return ResponseEntity.badRequest().body("L'inscription d'un administrateur n'est pas autorisée via cette API");
        }

        // Créer l'utilisateur en fonction du rôle
        User utilisateur;
        
        switch (request.getRole()) {
            case "Driver":
                utilisateur = new Taxi();
                break;
            case "Client":
                utilisateur = new Client();
                break;

            default:
                return ResponseEntity.badRequest().body("Rôle invalide. Les rôles autorisés sont : RESPONSABLE, CHEF_PROJET, MEMBRE_EQUIPE");
        }
        // Remplir les champs communs
        utilisateur.setFirstName(request.getNom());
        utilisateur.setFirstName(request.getPrenom());
        utilisateur.setEmail(request.getEmail());
        utilisateur.setPassword(passwordEncoder.encode(request.getMotDePasse())); // Hachage systématique
        utilisateur.setRoles(UserRoles.valueOf(request.getRole()));

        // Sauvegarder l'utilisateur
        utilisateurRepository.save(utilisateur);

        return ResponseEntity.ok("Utilisateur enregistré avec succès");
    }

    
}

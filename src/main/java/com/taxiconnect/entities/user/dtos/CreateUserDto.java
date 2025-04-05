package com.taxiconnect.entities.user.dtos;
import java.util.Set;

import com.taxiconnect.entities.roles.UserRoles;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {
    @NotBlank(message = "The firstname is required.")
    private String firstName;
    @NotBlank(message = "The lastname is required.")
    private String lastName;
    @NotBlank(message = "The email is required.")
    @Email(message = "The email is not valid.")
    private String email;
    @NotBlank(message = "The password is required.")
    private String password;
   // @NotBlank(message = "The roles are required.")
    private Set<UserRoles> roles;

    
}

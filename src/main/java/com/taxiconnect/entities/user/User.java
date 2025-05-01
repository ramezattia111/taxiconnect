
package com.taxiconnect.entities.user;
import java.util.List;

import com.taxiconnect.entities.roles.UserRoles;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Inheritance(strategy = InheritanceType.JOINED) // This is important!
@Data
public abstract class User {
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(nullable = false)
    private String firstName;
    
    @Column(nullable = false)
    private String lastName;
    
    @Column(unique = true)
    private String email;

    @Column()
    private String password;

    @ElementCollection(fetch = FetchType.EAGER, targetClass = UserRoles.class)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
        @Enumerated(EnumType.STRING)

    private UserRoles roles;
}
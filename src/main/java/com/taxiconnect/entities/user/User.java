package com.taxiconnect.entities.user;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.taxiconnect.entities.roles.UserRoles;
import com.taxiconnect.entities.taxi.Taxi;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
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

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "user_id"))
  @Column(name = "user_role")
  @Enumerated(EnumType.STRING)
  private Set<UserRoles> roles;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  @JsonIgnore
  private Set<Taxi> taxis;
}

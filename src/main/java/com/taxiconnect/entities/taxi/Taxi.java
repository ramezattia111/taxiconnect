package com.taxiconnect.entities.taxi;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.taxiconnect.entities.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "taxi")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Taxi {
    @Id
    @GeneratedValue
    private Long id;

    @Column()
    private boolean validated = false;

    @Column(unique = true, nullable = false)
    private String matricule;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false, unique = true)
    private Long taxiNumber;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;
}

package com.taxiconnect.entities.taxi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaxiDto {
    @NotBlank(message = "matricule is required")
    private  String matricule;
    @NotBlank(message = "model is required")
    private  String model;
    @NotBlank(message = "brand is required")
    private  String brand;
    @NotNull(message = "taxiNumber is required")
    private Long taxiNumber;
    @NotNull(message = "userId is required")
    private Long userId;
}

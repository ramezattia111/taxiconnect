package com.taxiconnect.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.taxiconnect.entities.taxi.Taxi;
import com.taxiconnect.entities.taxi.dtos.CreateTaxiDto;
import com.taxiconnect.services.TaxiServices;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/taxi")

public class TaxiController {
    @Autowired
    private TaxiServices taxiServices;

    @GetMapping("/all")
    public ResponseEntity<List<Taxi>> getTaxis() {
        List<Taxi> taxis = taxiServices.getTaxis();
        return new ResponseEntity<>(taxis, HttpStatus.OK);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<List<Taxi>> getTaxiByUser(
            @PathVariable Long id
    ) {
        List<Taxi> taxis = taxiServices.getTaxiByUser(id);
        return new ResponseEntity<>(taxis, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Taxi> createTaxi(
            @Valid @RequestBody CreateTaxiDto taxi) {
        Taxi createdTaxi = taxiServices.createTaxi(taxi);
        return new ResponseEntity<>(createdTaxi, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taxi> getTaxiById(
            @PathVariable Long id) {
        Taxi taxi = taxiServices.getTaxiById(id);
        return new ResponseEntity<>(taxi, HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Taxi> updateUser(@PathVariable long id) {
                return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long id) {
        return  new ResponseEntity<>(false, HttpStatus.NOT_FOUND);

    }

}

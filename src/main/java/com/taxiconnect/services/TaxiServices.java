package com.taxiconnect.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taxiconnect.entities.taxi.Taxi;
import com.taxiconnect.entities.taxi.dtos.CreateTaxiDto;
import com.taxiconnect.entities.user.User;
import com.taxiconnect.exceptions.ConflictNotFound;
import com.taxiconnect.exceptions.RessourceNotFound;
import com.taxiconnect.repo.TaxiRepo;

@Service
public class TaxiServices {
    @Autowired
    private TaxiRepo taxiRepo;
    @Autowired
    private UserServices userServices;

    public Taxi createTaxi(CreateTaxiDto taxi) {
        if (taxiRepo.existsByMatricule(taxi.getMatricule())) {
            throw new ConflictNotFound("Taxi with matricule " + taxi.getMatricule() + " already exists");
        }
        if (taxiRepo.existsByTaxiNumber(taxi.getTaxiNumber())) {
            throw new ConflictNotFound("Taxi with taxi number " + taxi.getTaxiNumber() + " already exists");
        }
        final User user = userServices.getUserById(taxi.getUserId());
        Taxi newTaxi = new Taxi();
        newTaxi.setMatricule(taxi.getMatricule());
        newTaxi.setModel(taxi.getModel());
        newTaxi.setBrand(taxi.getBrand());
        newTaxi.setTaxiNumber(taxi.getTaxiNumber());
        return taxiRepo.save(newTaxi);
    }

    public List<Taxi> getTaxis() {
        return taxiRepo.findAll();
    }

    public Taxi getTaxiById(Long id) {
        return taxiRepo.findById(id)
                .orElseThrow(() -> new RessourceNotFound("Taxi with id " + id + " not found"));
    }

    public List<Taxi> getTaxiByUser(Long id) {
       final User user= userServices.getUserById(id);
        return taxiRepo.findByUser(user);
    }

    public boolean deleteTaxi(Long id) {
        return true;
    }

}

x@package com.taxiconnect.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.taxiconnect.entities.taxi.Taxi;
import java.util.List;
import com.taxiconnect.entities.user.User;



public interface TaxiRepo extends JpaRepository<Taxi, Long> {
    boolean existsByMatricule(String matricule);
    boolean existsByTaxiNumber(Long taxiNumber);
    List<Taxi> findByUser(User user);

}

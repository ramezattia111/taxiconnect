package com.taxiconnect.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taxiconnect.entities.User;
import com.taxiconnect.repo.UserRepo;


@Service
public class UserServices {
    @Autowired
    private UserRepo userRepo;

    public User createUser(User u) {
      /*  User user = new User();
        user.setFirstName(u.getFirstName());
        user.setLastName(u.getLastName());
        user.setEmail(u.getEmail());
        user.setPassword(u.getPassword()); */
        return userRepo.save(u);

    }

    public List<User> getUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("User non trouvé"));
    }

    public boolean deleteUser(Long id) {
        userRepo.deleteById(id);
        return true;
    }
    

}
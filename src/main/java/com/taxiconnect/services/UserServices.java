package com.taxiconnect.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taxiconnect.entities.user.User;
import com.taxiconnect.entities.user.dtos.CreateUserDto;
import com.taxiconnect.entities.user.dtos.UpdateUserDto;
import com.taxiconnect.exceptions.RessourceNotFound;
import com.taxiconnect.repo.UserRepo;


@Service
public class UserServices {
    @Autowired
    private UserRepo userRepo;


    public User createUser(CreateUserDto user) {
        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword()); 
        newUser.setRoles(user.getRoles()); 
        return userRepo.save(newUser);

    }

    public List<User> getUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> findedUser = userRepo.findById(id);
        return findedUser.orElseThrow(() -> new RessourceNotFound("User with id " + id + " not found"));
    }

    public User updateUser(long id, UpdateUserDto user) {
            User existingUser = this.getUserById(id);
            if (user.getFirstName() != null) {
                existingUser.setFirstName(user.getFirstName());
            }
            if (user.getLastName() != null) {
                existingUser.setLastName(user.getLastName());
            }
            if (user.getEmail() != null) {
                existingUser.setEmail(user.getEmail());
            }
            if (user.getPassword() != null) {
                existingUser.setPassword(user.getPassword());
            }
            return userRepo.save(existingUser);
    }
    public boolean deleteUser(Long id) {
        User findUser = this.getUserById(id);
        userRepo.delete(findUser);
        return true;
    }
    

}
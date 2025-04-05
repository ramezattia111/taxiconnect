package com.taxiconnect.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.taxiconnect.entities.user.User;
import com.taxiconnect.entities.user.dtos.CreateUserDto;
import com.taxiconnect.entities.user.dtos.UpdateUserDto;
import com.taxiconnect.services.UserServices;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/users")
public class UserControllers {
    @Autowired
    private UserServices userServices;

    @GetMapping({ "/all", "/" })
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userServices.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping({ "create", "/" })
    public ResponseEntity<User> createUser(
            @Valid @RequestBody CreateUserDto user) {
        User createdUser = userServices.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(
            @PathVariable Long id) {
        User user = userServices.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id,
            @Valid @RequestBody UpdateUserDto User) {
        User updatedUser = userServices.updateUser(id, User);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long id) {
        boolean isDeleted = userServices.deleteUser(id);
        return new ResponseEntity<>(isDeleted, HttpStatus.OK);
    }

}

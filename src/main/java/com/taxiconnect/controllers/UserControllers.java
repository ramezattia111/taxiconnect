package com.taxiconnect.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

import com.taxiconnect.entities.User;
import com.taxiconnect.services.UserServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/users")
public class UserControllers {
    @Autowired
    private UserServices userServices;
    
    @GetMapping({"/all","/"})
    public ResponseEntity<List<User>> getAllUsers() {
     List<User> users=   userServices.getUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping({"create","/"})
    public ResponseEntity<User> createUser(
        @RequestBody User user
    ) {
        User createdUser = userServices.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getCoursById(@PathVariable Long id) {
        User user = userServices.getUserById(id);
        return ResponseEntity.ok(user);
    }
}

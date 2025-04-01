package com.taxiconnect.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.taxiconnect.entities.Test;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class TestControllers {
     private List<Test> testes= new ArrayList<>();
    @GetMapping("/all")
    public  List<Test> getAlltests(){
        return testes;
    }

    @PostMapping("/create")
    public  List<Test> createTest(
        @RequestBody Test newTest
    ){
        testes.add(newTest);
        return testes;
    }

    @DeleteMapping("/delete/{id}")
    public  Long deleteTest(
        @PathVariable Long id
    ){
        
        return id;
    }
}

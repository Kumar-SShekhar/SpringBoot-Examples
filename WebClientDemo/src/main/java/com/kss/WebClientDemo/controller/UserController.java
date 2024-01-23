package com.kss.WebClientDemo.controller;

import com.kss.WebClientDemo.model.User;
import com.kss.WebClientDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getWeb")
    public ResponseEntity<List<User>> getAllData(){
        return new ResponseEntity<>(userService.consumeApi() , HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<List<User>> getById(@PathVariable int id){
        return new ResponseEntity<>(userService.consumeApiById(id) , HttpStatus.OK);
    }
}

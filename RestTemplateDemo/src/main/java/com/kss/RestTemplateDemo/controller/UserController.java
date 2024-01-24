package com.kss.RestTemplateDemo.controller;

import com.kss.RestTemplateDemo.model.User;
import com.kss.RestTemplateDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllData() {
        return new ResponseEntity<>(userService.consumeAPI(), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<List<User>> getUserById(@PathVariable int id) {
        return new ResponseEntity<>(userService.consumeApiById(id), HttpStatus.OK);
    }

    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<List<User>> getUserByUserId(@PathVariable int userId) {
        return new ResponseEntity<>(userService.consumeApiByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/getByCompleted/{completed}")
    public ResponseEntity<List<User>> getUserByCompleted(@PathVariable boolean completed) {
        return new ResponseEntity<>(userService.consumeApiByCompleted(completed), HttpStatus.OK);
    }
}

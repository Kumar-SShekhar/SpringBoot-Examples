package com.kss.ConsumingRestApi.controller;

import com.kss.ConsumingRestApi.model.Todo;
import com.kss.ConsumingRestApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class HomeController {

    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Todo>> getAllData() {
        return new ResponseEntity<>(userService.consumeAPI(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<List<Todo>> getUserById(@PathVariable int id) {
        return new ResponseEntity<>(userService.consumeApiById(id), HttpStatus.OK);
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<List<Todo>> getUserByUserId(@PathVariable int userId) {
        return new ResponseEntity<>(userService.consumeApiByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/completed/{completed}")
    public ResponseEntity<List<Todo>> getUserByCompleted(@PathVariable boolean completed) {
        return new ResponseEntity<>(userService.consumeApiByCompleted(completed), HttpStatus.OK);
    }

    @GetMapping("/both/{userId}/id/{id}")
    public ResponseEntity<List<Todo>> getUserByUserIdAndId(@PathVariable int userId , @PathVariable int id){
        return new ResponseEntity<>(userService.consumeApiByUserIdAndId(userId , id) , HttpStatus.OK);
    }

}
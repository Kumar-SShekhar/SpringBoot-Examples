package com.kss.ConsumingRestApiDemo.controller;
import com.kss.ConsumingRestApiDemo.model.Todo;
import com.kss.ConsumingRestApiDemo.service.WebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/webclient")
public class WebClientController {

    @Autowired
    private WebClientService webClientService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Todo>> getAllData(){
        return new ResponseEntity<>(webClientService.consumeApi() , HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<List<Todo>> getById(@PathVariable int id){
        return new ResponseEntity<>(webClientService.consumeApiById(id) , HttpStatus.OK);
    }

    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<List<Todo>> getByUserId(@PathVariable int userId){
        return new ResponseEntity<>(webClientService.consumeApiByUserId(userId) , HttpStatus.OK);
    }

    @GetMapping("/getByCompleted/{completed}")
    public ResponseEntity<List<Todo>> getByCompleted(@PathVariable boolean completed){
        return new ResponseEntity<>(webClientService.consumeApiByCompleted(completed) , HttpStatus.OK);
    }
}

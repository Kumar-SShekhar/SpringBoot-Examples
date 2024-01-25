package com.kss.ConsumingRestApi.controller;

import com.kss.ConsumingRestApi.model.Todo;
import com.kss.ConsumingRestApi.service.RestClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restclient")
public class RestClientController {

    private final RestClientService restClientService;

    public RestClientController(RestClientService restClientService){
        this.restClientService=restClientService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Todo>> getAll(){
        return new ResponseEntity<>(restClientService.consumeApi(), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<List<Todo>> getById(@PathVariable int id){
        return new ResponseEntity<>(restClientService.consumeApiById(id) , HttpStatus.OK);
    }

    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<List<Todo>> getByUserId(@PathVariable int userId){
        return new ResponseEntity<>(restClientService.consumeApiByUserId(userId) , HttpStatus.OK);
    }

    @GetMapping("/getByCompleted/{completed}")
    public ResponseEntity<List<Todo>> getByCompleted(@PathVariable boolean completed){
        return new ResponseEntity<>(restClientService.consumeApiByCompleted(completed) , HttpStatus.OK);
    }
}

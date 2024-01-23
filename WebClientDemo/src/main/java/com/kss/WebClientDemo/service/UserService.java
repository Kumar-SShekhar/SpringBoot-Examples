package com.kss.WebClientDemo.service;
import com.kss.WebClientDemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private WebClient.Builder webclient;

    private final String baseUri = "https://jsonplaceholder.typicode.com/todos/";

    public List<User> consumeApi(){
//        return (List<User>) webclient.build()
//                .get()
//                .uri(baseUri)
//                .retrieve()
//                .bodyToMono(List.class)
//                .block();

        return webclient.build().get().uri(baseUri).retrieve().bodyToFlux(User.class).collectList().block();

        /* Below I used Webclient interface directly . We can use this without creating a bean of Webclient*/
//        return WebClient.builder().build().get().uri(baseUri).retrieve().bodyToFlux(User.class).collectList().block();

    }

    public List<User> consumeApiById(int id){
        String apiUrl = baseUri + "?id=" +id;
        return webclient.build()
                .get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(List.class)
                .block();
    }




}

package com.kss.ConsumingRestApiDemo.service;

import com.kss.ConsumingRestApiDemo.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class WebClientService {

    @Autowired
    private WebClient.Builder webclient;

    private final String baseUri = "https://jsonplaceholder.typicode.com/todos/";

    public List<Todo> consumeApi(){
        return webclient.build().get().uri(baseUri).retrieve().bodyToFlux(Todo.class).collectList().block();
    }

    public List<Todo> consumeApiById(int id){
        String apiUrl = baseUri + "?id=" +id;
        return webclient.build()
                .get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(List.class)
                .block();
    }

    public List<Todo>  consumeApiByUserId(int userId){
        String apiUrl = baseUri + "?userId=" + userId;
        return webclient.build().get().uri(apiUrl).retrieve().bodyToFlux(Todo.class).collectList().block();
    }

    public List<Todo>  consumeApiByCompleted(boolean completed){
        String apiUrl = baseUri + "?completed=" + completed;
        return webclient.build().get().uri(apiUrl).retrieve().bodyToFlux(Todo.class).collectList().block();
    }
}

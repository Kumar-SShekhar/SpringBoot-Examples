package com.kss.ConsumingRestApiDemo.service;

import com.kss.ConsumingRestApiDemo.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class RestClientService {

    private final RestClient.Builder restClient;

    @Autowired
    public RestClientService(RestClient.Builder restClient){
        this.restClient=restClient;
    }

    @Value("${jsonplaceholder.api.url}")
    private String baseUri;

    public List<Todo> consumeApi(){
        return restClient.build().get().uri(baseUri).retrieve().body(List.class);
    }

    public List<Todo> consumeApiById(int id){
        String apiUrl = baseUri + "?id=" + id;
        return restClient.build().get().uri(apiUrl).retrieve().body(List.class);
    }

    public List<Todo> consumeApiByUserId(int userId){
        String apiUrl = baseUri + "?userId=" + userId;
        return restClient.build().get().uri(apiUrl).retrieve().body(List.class);
    }

    public List<Todo> consumeApiByCompleted(boolean completed){
        String apiUrl = baseUri + "?completed=" + completed;
        return restClient.build().get().uri(apiUrl).retrieve().body(List.class);
    }

}
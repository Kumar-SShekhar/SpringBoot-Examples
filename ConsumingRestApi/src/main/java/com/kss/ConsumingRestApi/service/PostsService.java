package com.kss.ConsumingRestApi.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kss.ConsumingRestApi.model.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PostsService {

    private final RestTemplate restTemplate1;

    @Autowired
    public PostsService(RestTemplate restTemplate1){
        this.restTemplate1=restTemplate1;
    }

    private final String baseUri="https://dummyjson.com/posts";

//    public List<Posts> findAllPosts() {
//
////        return restTemplate1.getForObject(baseUri , List.class);
////        Posts[] posts = restTemplate1.getForObject(baseUri , Posts[].class);
////        return Arrays.asList(posts);
//    }

    public List<Posts> findAllPosts() {
        ResponseEntity<String> responseEntity = restTemplate1.getForEntity(baseUri, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String json = responseEntity.getBody();
            // Now manually deserialize the JSON string using your ObjectMapper or a JSON library
            ObjectMapper objectMapper = new ObjectMapper(); // Make sure you have Jackson ObjectMapper configured

            try {
                Posts[] postsArray = objectMapper.readValue(json, Posts[].class);
                return Arrays.asList(postsArray);
            } catch (IOException e) {
                // Handle the exception (log it or throw a custom exception)
                e.printStackTrace();
                return new ArrayList<>(); // or throw a custom exception if needed
            }
        } else {
            // Handle the case when the response status code is not successful.
            // ...
            return new ArrayList<>(); // or throw a custom exception if needed
        }
    }

    public Posts findPostsById(Integer id){
        return restTemplate1.getForObject(baseUri + "?id=" + id , Posts.class,id);
    }
}

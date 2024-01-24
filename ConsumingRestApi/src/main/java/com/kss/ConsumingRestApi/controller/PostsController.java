package com.kss.ConsumingRestApi.controller;

import com.kss.ConsumingRestApi.model.Posts;
import com.kss.ConsumingRestApi.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostsController {

    private PostsService postsService;

    @Autowired
    public  PostsController(PostsService postsService){
        this.postsService=postsService;
    }

    @GetMapping("/getPosts")
    public ResponseEntity<List<Posts>> getAllPosts(){
        return new ResponseEntity<>(postsService.findAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/getPosts/{id}")
    public ResponseEntity<Posts> getPostsById(@PathVariable Integer id){
        return new ResponseEntity<>(postsService.findPostsById(id), HttpStatus.OK);
    }
}

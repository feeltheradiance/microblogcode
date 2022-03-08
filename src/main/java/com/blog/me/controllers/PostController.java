package com.blog.me.controllers;

import com.blog.me.dao.PostRepository;
import com.blog.me.entities.Post;
import com.blog.me.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("posts")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    @GetMapping
    public ResponseEntity<List<Post>> getPostsList(){
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPost(@RequestParam Integer postId){
        return new ResponseEntity<>(postRepository.findByPostId(postId).get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Post> create(@RequestBody Post post){
        return new ResponseEntity<>(postRepository.save(post), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Post> update(@RequestBody Post postUpdated){
        Post post = postRepository.findByPostId(postUpdated.getPostId()).get();
        post.setContent(postUpdated.getContent());
        return new ResponseEntity<>(postRepository.save(post), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Post> delete(@RequestBody Post post){
        postRepository.deleteByPostId(post.getPostId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

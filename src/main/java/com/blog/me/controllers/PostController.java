package com.blog.me.controllers;

import com.blog.me.config.AppConfig;
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

    private Boolean answer = false;

    @Autowired
    PostRepository postRepository;

    @Autowired
    AppConfig.PubsubOutboundGateway outboundGateway;

    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }

    @GetMapping
    public ResponseEntity<List<Post>> getPostsList(){
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPost(@RequestParam Integer postId){
        return new ResponseEntity<>(postRepository.findByPostId(postId).get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Post> create(@RequestBody Post post) throws InterruptedException {
        outboundGateway.sendToPubsub(post.getContent());
        System.out.println("Content send: " + post.getContent());
        Thread.sleep(3000);
        if (answer.equals(true)) {
            System.out.println("Post saved");
            return new ResponseEntity<>(postRepository.save(post), HttpStatus.CREATED);
        } else {
            System.out.println("Post not saved");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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

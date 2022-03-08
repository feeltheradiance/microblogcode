package com.blog.me.controllers;

import com.blog.me.dao.CommentRepository;
import com.blog.me.dao.UserRepository;
import com.blog.me.entities.Comment;
import com.blog.me.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comments")
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @GetMapping
    public ResponseEntity<List<Comment>> getCommentsList(){
        return new ResponseEntity<>((List<Comment>)commentRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Comment> create(@RequestBody Comment comment){
        return new ResponseEntity<>(commentRepository.save(comment), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Comment> update(@RequestBody Comment commentUpdated){
        Comment comment = commentRepository.findByCommentId(commentUpdated.getCommentId()).get();
        comment.setContent(commentUpdated.getContent());
        return new ResponseEntity<>(commentRepository.save(comment), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<User> delete(@RequestBody Comment comment){
        commentRepository.deleteByCommentId(comment.getCommentId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

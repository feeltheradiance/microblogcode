package com.blog.me.controllers;

import com.blog.me.dao.PostRepository;
import com.blog.me.dao.UserRepository;
import com.blog.me.entities.Post;
import com.blog.me.entities.User;
import com.blog.me.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<User>> getUsersList(){
        return new ResponseEntity<>((List<User>)userRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestBody User userUpdated){
        User user = userRepository.findByUserId(userUpdated.getUserId()).get();
        user.setEmail(userUpdated.getEmail());
        user.setName(userUpdated.getName());
        user.setPassword(userUpdated.getPassword());
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<User> delete(@RequestBody User user){
        userRepository.deleteByUserId(user.getUserId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

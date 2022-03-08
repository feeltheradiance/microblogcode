package com.blog.me.services;

import com.blog.me.dao.PostRepository;
import com.blog.me.entities.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository routeRepository;

    public List<Post> getAllPosts(){
        return (List<Post>)routeRepository.findAll();
    }
}

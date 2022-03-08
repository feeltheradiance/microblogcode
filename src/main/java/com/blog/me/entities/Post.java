package com.blog.me.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "posts", schema = "public")
public class Post {

    @Id
    private Integer postId;
    private Integer userId;
    private String content;

    public Post() {}

    public Post(Integer postId, Integer userId, String content) {
        this.postId = postId;
        this.userId = userId;
        this.content = content;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

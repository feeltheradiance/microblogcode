package com.blog.me.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comments", schema = "public")
public class Comment {

    @Id
    private Integer commentId;
    private Integer userId;
    private Integer postId;
    private String content;

    public Comment() {
    }

    public Comment(Integer commentId, Integer userId, Integer postId, String content) {
        this.commentId = commentId;
        this.userId = userId;
        this.postId = postId;
        this.content = content;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

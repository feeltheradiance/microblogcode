package com.blog.me.dao;

import com.blog.me.entities.Comment;
import com.blog.me.entities.Post;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {

    @Query(value = "SELECT * FROM comments WHERE comment_id = :commentId", nativeQuery = true)
    Optional<Comment> findByCommentId(@Param("commentId") Integer commentId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM comments WHERE comment_id = :commentId", nativeQuery = true)
    void deleteByCommentId(@Param("commentId") Integer commentId);
}

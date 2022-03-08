package com.blog.me.dao;

import com.blog.me.entities.Post;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {

    @Query(value = "SELECT * FROM posts WHERE post_id = :postId", nativeQuery = true)
    Optional<Post> findByPostId(@Param("postId") Integer postId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM posts WHERE post_id = :postId", nativeQuery = true)
    void deleteByPostId(@Param("postId") Integer postId);
}

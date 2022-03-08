package com.blog.me.dao;

import com.blog.me.entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer>{

    @Query(value = "SELECT * FROM users WHERE user_id = :userId", nativeQuery = true)
    Optional<User> findByUserId(@Param("userId") Integer userId);

    @Query(value = "SELECT * FROM users WHERE email = :email AND password = :password", nativeQuery = true)
    Optional<User> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM users WHERE user_id = :userId", nativeQuery = true)
    void deleteByUserId(@Param("userId") Integer userId);
}

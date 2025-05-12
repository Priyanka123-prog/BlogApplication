package com.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findAllByNameContaining(String name);
}

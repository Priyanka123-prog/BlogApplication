package com.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

	List<Comment> findBypostId(Long postId);
}

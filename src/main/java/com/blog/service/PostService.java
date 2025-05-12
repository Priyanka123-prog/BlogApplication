package com.blog.service;

import java.util.List;

import com.blog.entity.Post;

public interface PostService {
	Post savePost(Post post);
	List<Post> getAllPosts();
	Post getPostById(Long postId);
	void likePost(Long postId) ;
	
	List<Post> searchByName(String name);
}

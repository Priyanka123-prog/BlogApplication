package com.blog.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Post;
import com.blog.repository.PostRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PostServiceImple implements PostService{
	 @Autowired
     private PostRepository postRepository;
	 
	 public Post savePost(Post post) {
		 post.setLikeCount(0);
		 post.setViewCount(0);
		 post.setDate(new Date());
		return postRepository.save(post);
	 }
	 
	 public List<Post> getAllPosts(){
		return postRepository.findAll();
	 }
	 
	 public Post getPostById(Long postId) {
		 Optional<Post> optionalPost=postRepository.findById(postId);
		 if(optionalPost.isPresent()) {
			  Post post=optionalPost.get();
			  post.setViewCount(post.getViewCount());
			  return postRepository.save(post);
		 }
		 else {
			 throw new EntityNotFoundException("Post not Found");
		 }
		 
	 }
	 
	 public void likePost(Long postId) {
		 Optional<Post> optionalPost= postRepository.findById(postId);
		 if(optionalPost.isPresent()) {
			 Post post=optionalPost.get();
			 post.setLikeCount(post.getLikeCount()+1);
			 postRepository.save(post);
		 }else {
			 throw new EntityNotFoundException("Post not Found with id:" +postId);
		 }
		 
	 }
	 
	 public List<Post> searchByName(String name){
		 return postRepository.findAllByNameContaining(name);
	 }
}

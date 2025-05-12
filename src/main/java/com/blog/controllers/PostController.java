package com.blog.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.entity.Post;
import com.blog.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins="*")
public class PostController {

	@Autowired
	private PostService postService;
	
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> createpost(@RequestParam("name") String name,
	        @RequestParam("content") String content,
	        @RequestParam("postedBy") String postedBy,
	        @RequestParam("tags") String tagsJson,
	        @RequestParam("img") MultipartFile file){
		try {
			Post post = new Post();
	        post.setName(name);
	        post.setContent(content);
	        post.setPostedBy(postedBy);
	        post.setTags(Arrays.asList(new ObjectMapper().readValue(tagsJson, String[].class)));
	        post.setImg(file.getBytes());
			Post createPost=postService.savePost(post);
			return ResponseEntity.status(HttpStatus.CREATED).body(createPost);
			
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
//	@PostMapping(value= {"/add"},consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
//	public ResponseEntity<?> createpost(@RequestPart("post") Post post,@RequestPart("img") MultipartFile[] file){
//		try {
//			Post createPost=postService.savePost(post);
//			return ResponseEntity.status(HttpStatus.CREATED).body(createPost);
//			
//		}catch(Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//		}
//	}
//	
	
	@GetMapping
	public ResponseEntity<List<Post>> getAllPosts(){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(postService.getAllPosts());
			
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<?> getPostById(@PathVariable Long postId){
		try {
			Post post=postService.getPostById(postId);
			return ResponseEntity.ok(post);
		}
		catch(EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
	}
	
	@PutMapping("/{postId}/like")
	public ResponseEntity<?> likePost(@PathVariable Long postId){
		try {
			postService.likePost(postId);
			return ResponseEntity.ok(new String[] {"Post Liked Successfully"});
			
		}catch(EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping("/search/{name}")
	public ResponseEntity<?> searchName(@PathVariable String name){
		try {
			
			return ResponseEntity.status(HttpStatus.OK).body(postService.searchByName(name));
			
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}

package com.networky.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.networky.demo.dtos.PostDTO;
import com.networky.demo.dtos.UserDTO;
import com.networky.demo.services.interfaces.PostService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins ="*" , allowedHeaders = "*" )
public class PostController {
	
	private PostService postService;

	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
	}

	@PostMapping("/getPost")
	public List<PostDTO> getPost(@RequestBody UserDTO user) {
		return postService.getPost(user);
	}
	
	@PostMapping("/savePost")
	public void savePost(@RequestBody PostDTO post) {
		postService.savePost(post);
	}
	
	@DeleteMapping("/deletePost")
	public void deletePost(@RequestBody PostDTO post) {
		postService.deletePost(post);
	}
	
}

package com.networky.demo.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.networky.demo.dtos.PostDTO;
import com.networky.demo.dtos.UserDTO;
import com.networky.demo.services.interfaces.PostService;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins ="*" , allowedHeaders = "*" )
public class PostController extends CrossOriginController {
	
	private PostService postService;

	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping("/getPosts")
	public List<PostDTO> getPosts(HttpServletRequest httpRequest) {
		String bearer = httpRequest.getHeader("Authentication");
		System.out.println("httpRequest : " + bearer);
		List<PostDTO> listPostDTO = null;
		try {
			
		listPostDTO = postService.getPosts(httpRequest);
			return listPostDTO;
		} catch (Exception e) {
		e.printStackTrace();
		}
		return null;
	}
	
	@PostMapping("/getPost")
	public List<PostDTO> getPost(@RequestBody UserDTO user) {
		System.out.println(user.toString());
		return postService.getPost(user);
	}
	
	@PostMapping("/savePost")
	public void savePost(@RequestBody PostDTO post) {
		System.out.println(post.toString());
		postService.savePost(post);
	}
	
	@DeleteMapping("/deletePost")
	public void deletePost(@RequestBody PostDTO post) {
		postService.deletePost(post);
	}
	
}

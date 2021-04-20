package com.networky.demo.services.interfaces;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.networky.demo.dtos.PostDTO;
import com.networky.demo.dtos.UserDTO;

public interface PostService {

	public List<PostDTO> getPost(UserDTO userId);
	
	public void savePost(PostDTO post);

//	public void deletePost(PostDTO post);

	public List<PostDTO> getPosts(HttpServletRequest httpRequest);
		
}

package com.networky.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.networky.demo.entities.Post;
import com.networky.demo.entities.User;

public interface PostDAO extends JpaRepository<Post, Integer> { //extends JpaRepository<Post, Integer> 
	
//	@Query("SELECT p FROM Post p WHERE p.idUser=:id")
	public List<Post> findPostByIdUser(@Param(value="IdUser") User user);

	public void deleteByIdUser(@Param(value="IdUser") User deletePost);
	

//	public void saveOrUpdatePost(Post post);
}

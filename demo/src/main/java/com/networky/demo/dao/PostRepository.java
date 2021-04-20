package com.networky.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.networky.demo.entities.Post;
import com.networky.demo.entities.User;

public interface PostRepository extends JpaRepository<Post, Integer> { //extends JpaRepository<Post, Integer> 
	
//	@Query("SELECT p FROM Post p WHERE p.idUser=:id")
	public List<Post> findPostByIdUser(@Param(value="idUser") User user);

//	public void deleteByIdUser(@Param(value="IdUser") User deletePost);

	@Query("FROM Post as p WHERE p.idUser.id =:idUser")
	public List<Post> findAllPostById(@Param(value = "idUser") int idUser);
	
//	public void saveOrUpdatePost(Post post);
}

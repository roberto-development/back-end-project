package com.networky.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.networky.demo.entities.Image;

public interface ImageRepository extends JpaRepository<Image, Integer>{
	
	
	 @Query(value = "SELECT i FROM Image i WHERE i.id= :id")
	public void deleteImageById(@Param(value="id") int id);
	
	
}
 
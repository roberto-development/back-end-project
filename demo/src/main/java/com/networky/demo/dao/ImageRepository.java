package com.networky.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.networky.demo.entities.Image;

public interface ImageRepository extends JpaRepository<Image, Integer>{

//	public void saveOrUpdateImage(Image image);
	
//	public byte[] blobToByte(User user);
//	public Image blobToByte(User user);

//	public Image updateImage(User user);
}
 
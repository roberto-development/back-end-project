package com.networky.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.networky.demo.dtos.ImageDTO;
import com.networky.demo.dtos.UserDTO;
import com.networky.demo.services.interfaces.ImageService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins ="*" , allowedHeaders = "*" )
public class ImageController {

	
	private ImageService imageService;

	@Autowired
	public ImageController(ImageService imageService) {
		this.imageService = imageService;
	}
	
	@PutMapping("/updateImage")
	public void saveOrUpdateImage(@RequestBody ImageDTO imageDTO) {
		imageService.saveOrUpdateImage(imageDTO);
	}
	
	@PostMapping("/getImage")
	public byte[] getImage(@RequestBody UserDTO userDTO) {
		System.out.println(userDTO.toString());
		return imageService.blobToByte(userDTO);
	}
	
	@DeleteMapping("/deleteImage")
	public void deleteImage(ImageDTO imageDTO) {
		imageService.deleteImage(imageDTO);
	}
	
//	@PostMapping("/getProfileImage")
//	public Image get
	
}

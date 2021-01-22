package com.networky.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.networky.demo.dtos.ImageDTO;
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
	public void updateImage(@RequestBody ImageDTO image) {
		imageService.updateImage(image);
	}
	
}

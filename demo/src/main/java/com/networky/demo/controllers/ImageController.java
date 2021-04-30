package com.networky.demo.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.networky.demo.dtos.ImageDTO;
import com.networky.demo.dtos.UserDTO;
import com.networky.demo.services.interfaces.ImageService;
import com.networky.demo.services.interfaces.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins ="*" , allowedHeaders = "*" )
public class ImageController extends CrossOriginController {


	private ImageService imageService;
	
	private UserService userService;

	@Autowired
	public ImageController(ImageService imageService, UserService userService) {
		this.imageService = imageService;
		this.userService = userService;
	}

	@PutMapping("/updateImage")
	public void saveOrUpdateImage(@RequestBody ImageDTO imageDTO) {
		imageService.saveOrUpdateImage(imageDTO);
	}

	@GetMapping("/getImage")
	public byte[] getImage(HttpServletRequest httpRequest) { //@RequestHeader("Authentication") HttpServletRequest httpRequest
		String bearer = httpRequest.getHeader("Authentication");
		System.out.println("httpRequest : " + bearer);
		byte[] getImage = null;
		UserDTO userDTOtoFind = new UserDTO();
		try {
			userDTOtoFind = userService.getUserInfo(httpRequest);
			System.out.println(userDTOtoFind.toString());
			
			if (userDTOtoFind != null) {
				getImage = imageService.blobToByte(userDTOtoFind);
			}
		} catch (Exception e) {
			e.printStackTrace();
//			 gestire errore per utente che non ha foto profilo
		}
		return getImage;
	}

	@DeleteMapping("/deleteImage")
	public void deleteImage(ImageDTO imageDTO) {
		imageService.deleteImage(imageDTO);
	}

}

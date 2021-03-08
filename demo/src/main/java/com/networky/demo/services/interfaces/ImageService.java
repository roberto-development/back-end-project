package com.networky.demo.services.interfaces;

import com.networky.demo.dtos.ImageDTO;
import com.networky.demo.dtos.UserDTO;
import com.networky.demo.entities.Image;
import com.networky.demo.entities.User;

public interface ImageService {

	public void saveOrUpdateImage(ImageDTO image);

	public byte[] blobToByte(UserDTO user);
	
	public Image getUserImage(User user);

	public void deleteImage(ImageDTO imageDTO);
}

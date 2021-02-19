package com.networky.demo.services.interfaces;

import com.networky.demo.dtos.ImageDTO;
import com.networky.demo.entities.Image;
import com.networky.demo.entities.User;

public interface ImageService {

	public void updateImage(ImageDTO image);

	public byte[] blobToByte(User user);
	
	public byte[] blobToArrayByte(int userId);

	public Image getUserImage(User user);
}

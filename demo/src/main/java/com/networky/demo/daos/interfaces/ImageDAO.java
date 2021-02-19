package com.networky.demo.daos.interfaces;

import java.util.List;

import com.networky.demo.entities.Image;
import com.networky.demo.entities.User;

public interface ImageDAO {

	public void saveOrUpdateImage(Image image);
	
	
	public String decodeBase64(User usersId);
	
	public byte[] blobToByte(User user);


	public byte[] blobToArrayByte(int userId);


//	Image updateImage(Image image);


	public Image updateImage(User user);
}
 
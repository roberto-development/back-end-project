package com.networky.demo.services.implementations;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.networky.demo.daos.interfaces.ImageDAO;
import com.networky.demo.daos.interfaces.UserDAO;
import com.networky.demo.dtos.ImageDTO;
import com.networky.demo.dtos.UserDTO;
import com.networky.demo.entities.Image;
import com.networky.demo.entities.User;
import com.networky.demo.mapper.MapperProvaImpl;
import com.networky.demo.services.interfaces.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

	private final ImageDAO imageDAO;
	private final UserDAO userDAO;
	
	private MapperProvaImpl mapperProva = new MapperProvaImpl();
	
	@Autowired
	public ImageServiceImpl(ImageDAO imageDAO, UserDAO userDAO) {
			this.imageDAO = imageDAO;
			this.userDAO = userDAO;
	};
	
	@Override
	@Transactional
	public void updateImage(ImageDTO image) {
			UserDTO newUserForImage = new UserDTO();
			newUserForImage.setId(image.getUsersId());
			newUserForImage.setImage(image.getImg());
			System.out.println("newUserForImage : " + newUserForImage.toString());
//			imageDAO.saveImage(image);
			
//			UserDTO to User
			User userConImage = mapperProva.DtoToUser(newUserForImage);
			
			Image imageEntity = new Image();
			imageEntity.setId(image.getId());
			imageEntity.setImg(image.getImg());
			imageEntity.setUsersId(userConImage);
			
			System.out.println("image dopo conv");
//			System.out.println(imageEntity.toString());
			imageDAO.saveOrUpdateImage(imageEntity);
	}

	@Override
	@Transactional
	public Image getUserImage(User user) {
		
			Image updateImageUser = new Image();
			updateImageUser = imageDAO.updateImage(user);
			System.out.println("updateImageUser : " + updateImageUser.toString());
			return updateImageUser;
//			UserDTO newUserForImage = new UserDTO();
//			newUserForImage.setId(image.getUsersId());
//			newUserForImage.setImage(image.getImg());
//			System.out.println("newUserForImage : " + newUserForImage.toString());
////			imageDAO.saveImage(image);
//			
////			UserDTO to User
//			User userConImage = mapperProva.DtoToUser(newUserForImage);
//			
//			Image imageEntity = new Image();
//			imageEntity.setImg(image.getImg());
//			imageEntity.setUsersId(userConImage);
//			
//			System.out.println("image dopo conv");
////			System.out.println(imageEntity.toString());
//			Image imageObject = new Image();
//			imageObject = imageDAO.updateImage(imageEntity);
//			return imageObject;
	}

	
	
	
	
	
//	returns image as byte array
	@Override
	public byte[] blobToByte(User user) {
		
//		delete after postman
		
//		User findIdUser = userDAO.findById(user.getId());
		System.out.println("user per getIMage" + user.toString());
//		if (findIdUser != null) {
			byte[] myByte = imageDAO.blobToByte(user);
//			byte[] encoded = Base64.getEncoder().encode(myByte);
			return myByte;
//			return encoded;
			
			
//		}
//		return null;
	}
	
	@Override
	public byte[] blobToArrayByte(int userId) {
		
//		delete after postman
		
//		User findIdUser = userDAO.findById(user.getId());
		
//		if (findIdUser != null) {
			byte[] myByte = imageDAO.blobToArrayByte(userId);
			
			return myByte;
	}
	
//	retrieve image when profile component is loaded

}

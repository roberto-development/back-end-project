package com.networky.demo.services.implementations;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.networky.demo.daos.interfaces.ImageDAO;
import com.networky.demo.dtos.ImageDTO;
import com.networky.demo.dtos.UserDTO;
import com.networky.demo.entities.Image;
import com.networky.demo.entities.User;
import com.networky.demo.mapper.MapperProvaImpl;
import com.networky.demo.services.interfaces.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

	private final ImageDAO imageDAO;
	
	private MapperProvaImpl mapperProva = new MapperProvaImpl();
	
	@Autowired
	public ImageServiceImpl(ImageDAO imageDAO) {
			this.imageDAO = imageDAO;
	}
	
	@Override
	@Transactional
	public void updateImage(ImageDTO image) {
			UserDTO newUserForImage = new UserDTO();
			newUserForImage.setId(image.getUsers_id());
			newUserForImage.setImage(image.getImg());
			
			User userConImage = mapperProva.DtoToUser(newUserForImage);
			
			Image imageEntity = new Image();
			imageEntity.setImg(image.getImg());
			imageEntity.setUsersId(userConImage);
			
			imageDAO.saveOrUpdateImage(imageEntity);
	}

}

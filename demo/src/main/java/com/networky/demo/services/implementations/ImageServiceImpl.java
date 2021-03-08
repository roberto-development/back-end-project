package com.networky.demo.services.implementations;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.networky.demo.dao.ImageDAO;
import com.networky.demo.dtos.ImageDTO;
import com.networky.demo.dtos.UserDTO;
import com.networky.demo.entities.Image;
import com.networky.demo.entities.User;
import com.networky.demo.mapper.ImageMapper;
import com.networky.demo.mapper.UserMapper;
import com.networky.demo.services.interfaces.ImageService;

@Service
public class ImageServiceImpl implements ImageService {
	
	@Autowired
	private EntityManager entityManager;

	private final ImageDAO imageDAO;
	
	private ImageMapper imgMapper = Mappers.getMapper(ImageMapper.class);
	
	private UserMapper mapper = Mappers.getMapper(UserMapper.class);
	
	@Autowired
	public ImageServiceImpl(ImageDAO imageDAO, UserMapper mapper) {
			this.imageDAO = imageDAO;
			this.mapper = mapper;
	};
	
	public Session getSession() {
		return entityManager.unwrap(Session.class);
	}
	
	
	@Override
	@Transactional
	public void saveOrUpdateImage(ImageDTO image) {
		
			UserDTO newUserForImage = setUserImage(image);
			User userConImage = mapper.DtoToEntityUser(newUserForImage);
			
			Image imageEntity = setImageEntity(image, userConImage);
			imageDAO.save(imageEntity);
	}

	@Override
	@Transactional
	public Image getUserImage(User user) {
		 
		Query<Image> theQuery = getSession().createQuery("from Image where users_id=:id", Image.class)
				.setParameter("id", user.getId());
		Image myBlobDb = theQuery.getSingleResult();
		Image imageToReturnFrontend = setImageEntityForGetMethod(myBlobDb);
		return imageToReturnFrontend;
		
//			Image updateImageUser = new Image();
//			updateImageUser.setUsersId(user.getId());
//			updateImageUser.setImg(user.get);
//			updateImageUser = imageDAO.updateImage(user);
//			Image obj = setImageEntity(updateI);
//			return updateImageUser;
	}

//	returns image as byte array
	@Override
	@Transactional
	public byte[] blobToByte(UserDTO user) {
		
			if ( user == null ) {
			return null;
			}
			
			User userEntity = mapper.DtoToEntityUser(user);
			Query<Image> theQuery = getSession().createQuery("from Image where users_id=:id", Image.class)
					.setParameter("id", userEntity.getId());
			Image myBlobDb = theQuery.getSingleResult();
			byte[] encoded = Base64.getEncoder().encode(myBlobDb.getImg());
			
//			byte[] myByte = imageDAO.blobToByte(userEntity);

			
//			Image myByte = imageDAO.
//			byte[] encoded = Base64.getEncoder().encode(myBlobDb.getImg());
			
			return encoded;
//			return myByte;
	}
	
	
	protected UserDTO setUserImage(ImageDTO image) {
		if( image == null ) {
			return null;
		}
		UserDTO newUserDTO = new UserDTO();
		newUserDTO.setId(image.getUsersId());
		List<ImageDTO> images = new ArrayList<>();
		for (ImageDTO imageDTO : images) {
			images.add(image);
		}
		newUserDTO.setImage(images);
		return newUserDTO;
	}
	
	protected Image setImageEntity(ImageDTO image, User user) {
		if(image == null | user == null) {
			return null;
		}
		Image imageEntity = new Image();
		imageEntity.setId(image.getId());
		imageEntity.setImg(image.getImg());
		imageEntity.setUsersId(user);
		
		return imageEntity;
	}
	
	protected Image setImageEntityForGetMethod(Image image) {
		Image newEntityImage = new Image();
		newEntityImage.setId(image.getId());
		newEntityImage.setImg(image.getImg());
		newEntityImage.setUsersId(image.getUsersId());
		return newEntityImage;
	}

	@Override
	public void deleteImage(ImageDTO imageDTO) {
		Image deleteImageEntity = imgMapper.imageDTOtoImageEntity(imageDTO);
		imageDAO.delete(deleteImageEntity);
		
		// TODO Auto-generated method stub
		
	}
	

}

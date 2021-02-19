package com.networky.demo.daos.implementations;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.util.Base64;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.stereotype.Repository;

import com.networky.demo.daos.interfaces.ImageDAO;
import com.networky.demo.entities.Image;
import com.networky.demo.entities.User;

@Repository
public class ImageDAOimpl implements ImageDAO {

	private final EntityManager entityManager;
	
	@Autowired
	public ImageDAOimpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	

//	public void saveImage(Image image) {
//		Session currentSession = entityManager.unwrap(Session.class);
//		System.out.println("dentro saveorupd : " + image.toString());
//		currentSession.saveOrUpdate(image);
//	}
	
	@Override
	public void saveOrUpdateImage(Image image) {
		Session currentSession = entityManager.unwrap(Session.class);
		System.out.println("dentro saveorupd : " + image.toString());
		currentSession.saveOrUpdate(image);
	}
	
	@Override
	public Image updateImage(User user) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Image> theQuery = currentSession.createQuery("from Image where users_id=:id", Image.class)
				.setParameter("id", user.getId());
		Image myBlobDb = theQuery.getSingleResult();
//		byte[] encoded = Base64.getEncoder().encode(myBlobDb.getImg());
		Image imageToReturnFrontend = new Image();
		imageToReturnFrontend.setImg(myBlobDb.getImg());
		imageToReturnFrontend.setId(myBlobDb.getId());
		imageToReturnFrontend.setUsersId(myBlobDb.getUsersId());
		System.out.println(imageToReturnFrontend.toString());
		return imageToReturnFrontend;
	}

	@Override
	public byte[] blobToByte(User user) {
		System.out.println(user.toString());
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Image> theQuery = currentSession.createQuery("from Image where users_id=:id", Image.class)
				.setParameter("id", user.getId());
		Image myBlobDb = theQuery.getSingleResult();
		System.out.println(myBlobDb.toString());
		
		byte[] encoded = Base64.getEncoder().encode(myBlobDb.getImg());
		
//		ByteArrayOutputStream bos = new ByteArrayOutputStream();
//		try(ObjectOutputStream oos = new ObjectOutputStream(bos)) {
//			System.out.println(bos);
//			oos.writeObject(b64);
//			System.out.println(myBlobDb);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		byte[] myBlob = bos.toByteArray();
		return encoded;
	}

	
	
	
	
	
	@Override
	public String decodeBase64(User usersId) {
//		// TODO Auto-generated method stub
//		Session currentSession = entityManager.unwrap(Session.class);
//		Query<Image> theQuery = currentSession.createQuery("from Image where users_id=:id", Image.class).setParameter("id", usersId.getId());
//		Image result = theQuery.getSingleResult();
//		Base64 base64;
//		String decodedString = new String(result.getImg());
//		System.out.println(decodedString.toString());
//		byte[] decodedData = DatatypeConverter.parseBase64Binary(decodedString);
//		System.out.println(decodedData.toString());
		return null;
	}
//
//
//	@Override
	public byte[] blobToArrayByte(int userId) {
//		Session currentSession = entityManager.unwrap(Session.class);
////		Query<byte[]> prevQuery = currentSession.createQuery("from Image where users_id=:id\", Image.class").setParameter("id", user.getId());
////		List<byte[]> result2 = prevQuery.getResultList();
//		
//		Query<Image> theQuery = currentSession.createQuery("from Image where users_id=:id", Image.class).setParameter("id", userId);
//		List<Image> result = theQuery.getResultList();
//		ByteArrayOutputStream bos = new ByteArrayOutputStream();
//		try(ObjectOutputStream oos = new ObjectOutputStream(bos)) {
//			
//			oos.writeObject(result);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		byte[] myBlob = bos.toByteArray();
////		Blob blob = theQuery.getResultList();
//		System.out.println(result.toString());
		return null;
	}
//	
	

}

package com.networky.demo.entities;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "Image")
@Table(name="images")
public class Image implements Serializable {
	
//	 Serializable implemented because of error java.io.NotSerializableException
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name="img")
	private byte[] img;
	
//	(targetEntity = User.class, cascade = CascadeType.ALL)
	@ManyToOne(targetEntity = com.networky.demo.entities.User.class)
	@JoinColumn(name = "users_id")
	private User usersId;
	
	public Image() {
	}

	public Image(byte[] img, User usersId) {
		this.img = img;
		this.usersId = usersId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public User getUsersId() {
		return usersId;
	}

	public void setUsersId(User usersId) {
		this.usersId = usersId;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", img=" + Arrays.toString(img) + ", usersId=" + usersId + "]";
	}


	
	
	

}

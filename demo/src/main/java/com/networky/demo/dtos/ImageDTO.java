package com.networky.demo.dtos;

import java.util.Arrays;

public class ImageDTO {
	
	private int id;

	private int usersId;
	
	private byte[] img;

	public ImageDTO() { }

	public ImageDTO(int id, int usersId, byte[] img) {
		this.id = id;
		this.usersId = usersId;
		this.img = img;
	}

	public int getUsersId() {
		return usersId;
	}

	public void setUsersId(int usersId) {
		this.usersId = usersId;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ImageDTO [usersId=" + usersId + ", img=" + Arrays.toString(img) + "]";
	}
	
}

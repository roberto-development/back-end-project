package com.networky.demo.dtos;

import java.util.Arrays;

public class ImageDTO {

	private int users_id;
	
	private byte[] img;

	public ImageDTO() { }

	public ImageDTO(int users_id, byte[] img) {
		this.users_id = users_id;
		this.img = img;
	}

	public int getUsers_id() {
		return users_id;
	}

	public void setUsers_id(int users_id) {
		this.users_id = users_id;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "ImageDTO [users_id=" + users_id + ", img=" + Arrays.toString(img) + "]";
	}
	
}

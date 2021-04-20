package com.networky.demo.dtos;

import java.util.Arrays;

public class PostDTO {

	private int id;

	private String descrizionePost;

//	deve diventare stringa
	private String categoriaPost;

//	autore 
	private int idUser;

	private byte[] imgPost;

	public PostDTO() {
	}

	public PostDTO(String descrizionePost, String categoriaPost, int idUser, byte[] imgPost) {
		this.descrizionePost = descrizionePost;
		this.categoriaPost = categoriaPost;
		this.idUser = idUser;
		this.imgPost = imgPost;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescrizionePost() {
		return descrizionePost;
	}

	public void setDescrizionePost(String descrizionePost) {
		this.descrizionePost = descrizionePost;
	}

	public String getCategoriaPost() {
		return categoriaPost;
	}

	public void setCategoriaPost(String categoriaPost) {
		this.categoriaPost = categoriaPost;
	}
	
	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public byte[] getImgPost() {
		return imgPost;
	}

	public void setImgPost(byte[] imgPost) {
		this.imgPost = imgPost;
	}

	@Override
	public String toString() {
		return "PostDTO [id=" + id + ", descrizionePost=" + descrizionePost + ", categoriaPost=" + categoriaPost
				+ ", idUser=" + idUser + ", imgPost=" + Arrays.toString(imgPost) + "]";
	}

}

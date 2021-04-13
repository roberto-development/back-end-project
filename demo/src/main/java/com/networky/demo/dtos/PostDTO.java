package com.networky.demo.dtos;

import java.util.Arrays;

public class PostDTO {

	private int id;

	private String descrizione;

//	deve diventare stringa
	private String categoria;

//	autore 
	private int idUser;

	private byte[] img;

	public PostDTO() {
	}


	public PostDTO(String descrizione, String categoria, int idUser, byte[] img) {
		this.descrizione = descrizione;
		this.categoria = categoria;
		this.idUser = idUser;
		this.img = img;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "PostDTO [id=" + id + ", descrizione=" + descrizione + ", categoria=" + categoria + ", idUser=" + idUser
				+ ", img=" + Arrays.toString(img) + "]";
	}
	
	

}

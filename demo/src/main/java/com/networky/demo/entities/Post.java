package com.networky.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "posts")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "descrizione")
	private String descrizione;
	
	@ManyToOne(targetEntity = Categoria.class)
	@JoinColumn(name = "categoria")
	private Categoria categoria;
	
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "id_user")
	private User idUser;
	
	@Lob
	@Column(name = "img")
	private byte[] img;

	public Post() {
	}

	public Post(int id, String descrizione, Categoria categoria, User idUser, byte[] img) {
		this.id = id;
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public User getIdUser() {
		return idUser;
	}

	public void setIdUser(User idUser) {
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
		return "Post [id=" + id + ", descrizione=" + descrizione + ", categoria=" + categoria + ", id_user=" + idUser
				+ "]";
	}
	

}

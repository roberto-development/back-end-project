package com.networky.demo.entities;

import java.util.Arrays;

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
	private String descrizionePost;
	
	@ManyToOne(targetEntity = Categoria.class)
	@JoinColumn(name = "categoria")
	private Categoria categoriaPost;
	
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "id_user")
	private User idUser;
	
//	riferimento a commenti? like? 
	
	@Lob
	@Column(name = "img")
	private byte[] imgPost;

	public Post() {
	}

	public Post(String descrizionePost, Categoria categoriaPost, User idUser, byte[] imgPost) {
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

	public Categoria getCategoriaPost() {
		return categoriaPost;
	}

	public void setCategoriaPost(Categoria categoriaPost) {
		this.categoriaPost = categoriaPost;
	}

	public User getIdUser() {
		return idUser;
	}

	public void setIdUser(User idUser) {
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
		return "Post [id=" + id + ", descrizionePost=" + descrizionePost + ", categoriaPost=" + categoriaPost
				+ ", autorePost=" + idUser + ", imgPost=" + Arrays.toString(imgPost) + "]";
	}

}

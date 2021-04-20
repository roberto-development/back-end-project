package com.networky.demo.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "categoria_post")
public class Categoria implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "nome_categoria")
	private String nomeCategoria;
	
	@OneToMany(mappedBy = "categoriaPost", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Post> idPost;

	public Categoria() {
	}

	public Categoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public List<Post> getIdPost() {
		return idPost;
	}

	public void setIdPost(List<Post> idPost) {
		this.idPost = idPost;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nomeCategoria=" + nomeCategoria + ", idPost=" + idPost + "]";
	}
	
	

	
}

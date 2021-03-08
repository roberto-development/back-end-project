package com.networky.demo.dtos;

import java.util.List;

import com.networky.demo.entities.Post;

public class CategoriaDTO {

	private int id;
	
	private String nomeCategoria;
	
	private List<Post> idPost;
	
	public CategoriaDTO() {
	}

	public CategoriaDTO(String nomeCategoria, List<Post> idPost) {
		this.nomeCategoria = nomeCategoria;
		this.idPost = idPost;
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
	
}

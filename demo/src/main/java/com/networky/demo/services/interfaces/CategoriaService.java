package com.networky.demo.services.interfaces;

import com.networky.demo.dtos.CategoriaDTO;
import com.networky.demo.entities.Categoria;

public interface CategoriaService {
	
	public Categoria getCategoria(String nomeCategoria);
	
	public void deleteCategoria(CategoriaDTO categoriaDTO);

	public CategoriaDTO getCategoria(int id);
}

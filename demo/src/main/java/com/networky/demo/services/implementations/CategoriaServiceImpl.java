package com.networky.demo.services.implementations;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.networky.demo.dao.CategoriaDAO;
import com.networky.demo.dtos.CategoriaDTO;
import com.networky.demo.entities.Categoria;
import com.networky.demo.mapper.CategoriaMapper;
import com.networky.demo.services.interfaces.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {
	
	CategoriaMapper mapper = Mappers.getMapper(CategoriaMapper.class);
	
	private final CategoriaDAO categoriaDAO;

	@Autowired
	public CategoriaServiceImpl(CategoriaDAO categoriaDAO) {
		this.categoriaDAO = categoriaDAO;
	}

	@Override
	public void deleteCategoria(CategoriaDTO categoriaDTO) {
		Categoria categoriaEntity = mapper.dtoToEntity(categoriaDTO);
		categoriaDAO.delete(categoriaEntity);
	}

}

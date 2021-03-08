package com.networky.demo.mapper;

import org.mapstruct.Mapper;

import com.networky.demo.dtos.CategoriaDTO;
import com.networky.demo.entities.Categoria;

@Mapper(componentModel = "spring", uses = {PostMapper.class})
public interface CategoriaMapper {

	
	public CategoriaDTO entityToDTO(Categoria categoria);
	
	public Categoria dtoToEntity(CategoriaDTO categoriaDTO);
}

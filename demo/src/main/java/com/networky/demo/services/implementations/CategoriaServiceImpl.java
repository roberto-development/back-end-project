package com.networky.demo.services.implementations;

import java.util.Optional;

import javax.transaction.Transactional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.networky.demo.dtos.CategoriaDTO;
import com.networky.demo.entities.Categoria;
import com.networky.demo.mapper.CategoriaMapper;
import com.networky.demo.repository.CategoriaRepository;
import com.networky.demo.services.interfaces.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {
	
	CategoriaMapper mapper = Mappers.getMapper(CategoriaMapper.class);
	
	private final CategoriaRepository categoriaDAO;

	@Autowired
	public CategoriaServiceImpl(CategoriaRepository categoriaDAO) {
		this.categoriaDAO = categoriaDAO;
	}

	@Override
	@Transactional
	public void deleteCategoria(CategoriaDTO categoriaDTO) {
		Categoria categoriaEntity = mapper.dtoToEntity(categoriaDTO);
		categoriaDAO.delete(categoriaEntity);
	}

	@Override
	@Transactional
	public CategoriaDTO getCategoria(int id) {
		CategoriaDTO catDTO = null;
		System.out.println("id cat " + id);
		Optional<Categoria> cat = categoriaDAO.findById(id);
		System.out.println(cat.toString());
		if(cat.isPresent()) {
			Categoria categ = null;
			categ.setNomeCategoria(cat.get().getNomeCategoria());
			System.out.println(categ.toString());
			catDTO = mapper.entityToDTO(categ);
		}
		return catDTO;

	}
	
	@Override
	@Transactional
	public Categoria getCategoria(String nomeCategoria) {
//		CategoriaDTO categoriaPostDTO = null;
		Categoria cat = categoriaDAO.findByNomeCategoria(nomeCategoria);
//		Categoria categoria = null;
		if(cat != null) {
			System.out.println(cat.toString());
			return cat;
//			categoriaPostDTO = mapper.entityToDTO(categoria);
		}
		return null;
	}

}

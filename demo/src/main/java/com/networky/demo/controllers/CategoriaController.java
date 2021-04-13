package com.networky.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.networky.demo.dtos.CategoriaDTO;
import com.networky.demo.services.interfaces.CategoriaService;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins ="*" , allowedHeaders = "*" )
public class CategoriaController extends CrossOriginController {

	private CategoriaService categoriaService;

	@Autowired
	public CategoriaController(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}
	
	@DeleteMapping("/delete")
	public void deleteCategoria(@RequestBody CategoriaDTO categoriaDTO) {
		categoriaService.deleteCategoria(categoriaDTO);
	}
	
	@PostMapping("/getCategoria")
	public CategoriaDTO getCategoria(@RequestBody int id) {
		return categoriaService.getCategoria(id);
	}
	
}

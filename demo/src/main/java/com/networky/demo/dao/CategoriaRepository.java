package com.networky.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.networky.demo.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

	
	@Query("FROM Categoria as c WHERE c.nomeCategoria =:nomeCategoria")
	public Categoria findByNomeCategoria(@Param(value = "nomeCategoria") String nomeCategoria);

}

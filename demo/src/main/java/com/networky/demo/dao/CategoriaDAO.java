package com.networky.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.networky.demo.entities.Categoria;

public interface CategoriaDAO extends JpaRepository<Categoria, Integer> {

}

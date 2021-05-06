package com.networky.demo.services.interfaces;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;

public interface GenericService {

    public List<T> findAll();
    public void save(T entity);
    T findById(long id);
    public void delete(T entity);
    public void deleteById(long id);
    public long count();
	
}

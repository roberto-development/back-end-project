package com.networky.demo.mapper;

import org.mapstruct.Mapper;

import com.networky.demo.dtos.StatusDTO;
import com.networky.demo.entities.Status;

@Mapper(componentModel = "spring")
public interface StatusMapper {
	
	public Status dtoToStatusEntity(StatusDTO statusDTO);
	
	public StatusDTO entityToStatusDTO(Status status);

}

package com.networky.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.networky.demo.dtos.UserDTO;
import com.networky.demo.entities.User;

@Mapper(componentModel = "spring", uses = {PostMapper.class, ImageMapper.class})
public interface UserMapper {
	
	@Mapping(target = "image", ignore = true)
	public User DtoToEntityUser(UserDTO userDTO);
	
	@Mappings({
		@Mapping(target = "newNome", ignore = true),
		@Mapping(target = "newCognome", ignore = true),
		@Mapping(target = "newDataDiNascita", ignore = true),
		@Mapping(target = "newCountry", ignore = true),
		@Mapping(target = "image", ignore = true)
	})
	public UserDTO entityToUserDTO(User user);
	
	
	@Mappings({
		@Mapping(source = "newNome", target = "nome"),
		@Mapping(source = "newCognome", target = "cognome"),
		@Mapping(source = "newDataDiNascita", target = "dataDiNascita"),
		@Mapping(source = "newCountry", target = "country"),
		@Mapping(target = "image", ignore = true)
	})
	public User newFieldsToEntity(UserDTO userDTO);
		
}
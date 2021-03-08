package com.networky.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.networky.demo.dtos.ImageDTO;
import com.networky.demo.entities.Image;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ImageMapper {

	@Mapping(source = "usersId", target = "usersId.id")
	public Image imageDTOtoImageEntity(ImageDTO imageDTO);

	@Mapping(source = "usersId.id", target = "usersId")
	public ImageDTO imageEntityToImageDTO(Image image);
	
	public List<Image> imageDtoToEntity(List<ImageDTO> imageDTO);
	
	public List<ImageDTO> imageEntitytoDTO(List<Image> image);
	
}

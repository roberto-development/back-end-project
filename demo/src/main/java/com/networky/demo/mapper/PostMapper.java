package com.networky.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.networky.demo.dtos.PostDTO;
import com.networky.demo.entities.Post;

//@DecoratedWith(MapperDecoratorPost.class)
@Mapper(componentModel = "spring")
public interface PostMapper {
	
//	PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);
	
	@Mappings({ 
		@Mapping(source = "categoriaPost", target = "categoriaPost.nomeCategoria"),
		@Mapping(source = "idUser", target = "idUser.id")
		})
	public Post postDtoToPostEntity(PostDTO post);
		
	@Mappings({ 
		@Mapping(target = "categoriaPost", source = "categoriaPost.nomeCategoria"),
		@Mapping(target = "idUser", source = "idUser.id")
	})
	public PostDTO postEntityToPostDTO(Post post);
	
		
	public List<PostDTO> postEntityListToDTO(List<Post> post);
	
		
	public List<Post> postDTOListToEntity(List<PostDTO> postDTO);
}

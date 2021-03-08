package com.networky.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.networky.demo.dtos.FriendshipDTO;
import com.networky.demo.entities.Friendship;

@Mapper(componentModel = "spring")
public interface FriendshipMapper {
	
	@Mappings({ 
		@Mapping(source = "idSender", target = "idSender.id"),
		@Mapping(source = "idRecipient", target = "idRecipient.id"),
		@Mapping(source = "status", target = "status.id")
		})
	public Friendship dtoToFriendshipEntity(FriendshipDTO friendshipDTO);
	
	@Mappings({ 
		@Mapping(source = "idSender.id", target = "idSender"),
		@Mapping(source = "idRecipient.id", target = "idRecipient"),
		@Mapping(source = "status.id", target = "status")
		})
	public FriendshipDTO entityToFriendshipDTO(Friendship friendship);

}

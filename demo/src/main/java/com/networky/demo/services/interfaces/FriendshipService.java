package com.networky.demo.services.interfaces;

import java.util.List;

import com.networky.demo.dtos.FriendshipDTO;
import com.networky.demo.entities.Friendship;
import com.networky.demo.entities.Status;
import com.networky.demo.entities.User;

public interface FriendshipService {
	
	List<Friendship> getFriendships(FriendshipDTO friendshipDTO);
	
	Friendship saveFriendship(User idSender, User idRecipient, Status status);
	
	void sendFriendship(FriendshipDTO friendship);
	
	void deleteFriendship(FriendshipDTO friendshipDTO);
	
}

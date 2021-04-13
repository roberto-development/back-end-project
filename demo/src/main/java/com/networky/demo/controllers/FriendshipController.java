package com.networky.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.networky.demo.dtos.FriendshipDTO;
import com.networky.demo.entities.Friendship;
import com.networky.demo.services.interfaces.FriendshipService;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins ="*" , allowedHeaders = "*" )
public class FriendshipController extends CrossOriginController {

	private FriendshipService friendService;
	
	@Autowired
	public FriendshipController(FriendshipService friendService) {
		this.friendService = friendService;
	}
	
//	every request has pending status
	
	@PostMapping("/getFriendship")
	public List<Friendship> getFriendships(@RequestBody FriendshipDTO friendshipDTO) {
		return friendService.getFriendships(friendshipDTO);
	}


	@PostMapping("/sendFriendship")
	void sendFriendshipRequest(@RequestBody FriendshipDTO friendshipDTO) {
		try {
		friendService.sendFriendship(friendshipDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@DeleteMapping("/deleteFriend")
	public void deleteFriendship(@RequestBody FriendshipDTO friendshipDTO) {
	try {
		friendService.deleteFriendship(friendshipDTO);
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
}

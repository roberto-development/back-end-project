package com.networky.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.networky.demo.dtos.AccountDTO;
import com.networky.demo.dtos.UserDTO;
import com.networky.demo.services.interfaces.UserService;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins ="*" , allowedHeaders = "*" )
public class UserController extends CrossOriginController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	


//	@PostMapping("/login")
//	public UserDTO login(@RequestBody AccountDTO accountDTO) {
//		System.out.println(accountDTO.toString());
//		UserDTO userLogin = userService.getUser(accountDTO);
//		return userLogin;
//	}
//	
//	@PostMapping("/getUser")
//	public ResponseEntity<UserDTO> signIn(@RequestBody AccountDTO accountDTO) {
//		ResponseEntity<UserDTO> userDTO = userService.getProfileUser(accountDTO);
////		non restituire dto 
//		return userDTO;
//	}

	@PutMapping("/userUpdate")
	public UserDTO updateUser(@RequestBody UserDTO user) {
		return userService.updateUser(user);

	}
}
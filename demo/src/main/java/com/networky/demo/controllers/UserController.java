package com.networky.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.networky.demo.dtos.AccountDTO;
import com.networky.demo.dtos.UserDTO;
import com.networky.demo.entities.Account;
import com.networky.demo.entities.User;
import com.networky.demo.services.interfaces.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins ="*" , allowedHeaders = "*" )
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/login")
	public UserDTO getUser(@RequestBody Account account) {

		UserDTO userLogin = userService.getUser(account);
		return userLogin;

	}

	@PostMapping("/register")
	public User registerUser(@RequestBody AccountDTO account) {
		System.out.println("\n Controller saveuser : " + account.toString());

		return  userService.saveUser(account);


	}

//	@PutMapping("/update")
//	public AccountDTO updateAccount(@RequestBody AccountDTO account) {
//		return userService.updateAccount(account);
//	}

	@PutMapping("/updateUser")
	public UserDTO updateUser(@RequestBody AccountDTO account) {
		System.out.println("\nController: account param : " + account.toString());
		return userService.updateUser(account);
	}

}
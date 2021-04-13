package com.networky.demo.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.networky.demo.dtos.UserDTO;
import com.networky.demo.services.interfaces.UserService;

@RestController
@RequestMapping("/api")
public class TokenController extends CrossOriginController {

	private UserService userService;

	@Autowired
	public TokenController(UserService userService) {
		this.userService = userService;
	}


	//	chiamata dricevuta da fEnd che invia il token da decifrare e ritornare le userInfo
	@GetMapping("/getUserInfo")
	public UserDTO getUserInfo(HttpServletRequest httpRequest) {
		String bearer = httpRequest.getHeader("Authentication");
		System.out.println("\nhttpRequest : " + bearer);
		UserDTO userDTO = new UserDTO();
		try {
			userDTO = userService.getUserInfo(httpRequest);
//			if (userDTO != null) {
				
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userDTO;
	}



}

package com.networky.demo.services.interfaces;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.networky.demo.dtos.AccountDTO;
import com.networky.demo.dtos.TokenDTO;
import com.networky.demo.dtos.UserDTO;
import com.networky.demo.entities.User;

public interface UserService {

	
//	public UserDTO getUser(AccountDTO accountDTO);

//	public UserDTO updateUser(AccountDTO accountDTO);
	
	public UserDTO getUserInfo(HttpServletRequest httpRequest);
	
	public UserDTO updateUser(UserDTO userDTO);

	public ResponseEntity<TokenDTO> getProfileUser(AccountDTO accountDTO);
	
	public List<User> listAll();
}

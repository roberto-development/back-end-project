package com.networky.demo.services.interfaces;

import com.networky.demo.dtos.AccountDTO;
import com.networky.demo.dtos.UserDTO;

public interface UserService {

	
	public UserDTO getUser(AccountDTO accountDTO);

//	public UserDTO updateUser(AccountDTO accountDTO);
	
	public UserDTO updateUser(UserDTO userDTO);

	
}

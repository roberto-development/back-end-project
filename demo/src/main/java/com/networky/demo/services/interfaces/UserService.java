package com.networky.demo.services.interfaces;

import com.networky.demo.dtos.AccountDTO;
import com.networky.demo.dtos.UserDTO;
import com.networky.demo.entities.Account;
import com.networky.demo.entities.User;

public interface UserService {

	
	public User saveUser(AccountDTO account);
	
	public UserDTO getUser(Account account);

//	public AccountDTO updateEmail(AccountDTO account);
//
//	public AccountDTO updatePassword(AccountDTO account);

//	public AccountDTO updateAccount(AccountDTO account);
	
	public UserDTO updateUser(AccountDTO account);
	
	public User updateUser(User user);

//	public void updateImage(ImageDTO image);

}

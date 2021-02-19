package com.networky.demo.daos.interfaces;

import com.networky.demo.entities.Account;
import com.networky.demo.entities.User;

public interface UserDAO {

	public Account getUser(Account account);
	
	public User saveUser(Account account);

	public User updateUser(Account account);
	
//	public Account updateEmail(Account account);
//
//	public Account updatePassword(Account accountCheck);

//	public Account updateAccount(Account accountCheck);
	
	public User toUpdateUser(User user);

	public User findById(int usersId);

//	public User updateImage(User newUserForImage);
	
//	public void saveOrUpdateImage(Image image);
	
	
}

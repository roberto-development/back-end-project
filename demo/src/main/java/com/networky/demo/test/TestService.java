package com.networky.demo.test;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.networky.demo.dtos.AccountDTO;
import com.networky.demo.entities.Account;
import com.networky.demo.entities.User;

@Service
public class TestService {

	private final UserRepository userRepo;
	
	@Autowired
	public TestService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

//	@Transactional
//	public List<Account> getUserById(String email) {
//		return userRepo.findByEmailQuery(email);
//	}
	
	@Transactional
	public void saveAccount(Account account) {
		userRepo.save(account);
	}
	
//	@Transactional
//	public List<Account> saveUser(Account account) {
//		return userRepo.saveUser(account);
//	}

//	@Transactional
//	public void saveUser(AccountDTO accountDto) {
//		return userRepo.saveAccountById(accountDto);
//	}

//	public List<User> saveUserByEmail(AccountDTO accountDto) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	
}

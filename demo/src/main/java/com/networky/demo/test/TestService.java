package com.networky.demo.test;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.networky.demo.daos.UserRepository;
import com.networky.demo.entities.Account;
import com.networky.demo.entities.User;

@Service
public class TestService {

	private final UserRepository userRepo;
	
	@Autowired
	public TestService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Transactional
	public List<Account> getUserByEmail(String email) {
		return userRepo.findByEmailQuery(email);
	}
	
	@Transactional
	public List<Account> saveUser(Account account) {
		return userRepo.saveUser(account);
	}
	
	
}

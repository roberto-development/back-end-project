package com.networky.demo.services.interfaces;

import com.networky.demo.dtos.AccountDTO;
import com.networky.demo.entities.Account;
import com.networky.demo.entities.User;

public interface AccountService {
	
	public AccountDTO updateAccount(AccountDTO account);

	public void deleteAccount(AccountDTO accountDTO); 
	
	public User saveAccount(AccountDTO accountDTO);

	public Account getAccount (AccountDTO account);

}

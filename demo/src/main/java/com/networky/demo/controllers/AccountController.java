package com.networky.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.networky.demo.dtos.AccountDTO;
import com.networky.demo.services.interfaces.AccountService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins ="*" , allowedHeaders = "*" )
public class AccountController {
	
	private AccountService accountService;
	
	
	@Autowired
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}


	@CrossOrigin(origins = "http://localhost:4200/reset-password", allowedHeaders = {})
	@PutMapping("/update")
	public AccountDTO updateAccount(@RequestBody AccountDTO account) {
		System.out.println("\nController account : " + account.toString());
		return accountService.updateAccount(account);
	}

}

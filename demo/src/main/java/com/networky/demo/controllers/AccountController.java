package com.networky.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.networky.demo.dtos.AccountDTO;
import com.networky.demo.dtos.TokenDTO;
import com.networky.demo.entities.User;
import com.networky.demo.exceptions.UserNotFoundException;
import com.networky.demo.exceptions.UserRestExceptionHandler;
import com.networky.demo.services.interfaces.AccountService;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins ="*" , allowedHeaders = "*" )
public class AccountController extends CrossOriginController {

	private AccountService accountService;

	UserRestExceptionHandler handler;

	UserNotFoundException except;

	@Autowired
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AccountDTO accountDTO) {
		ResponseEntity<TokenDTO> authenticatedAccount = accountService.login(accountDTO);
		try {
			if(authenticatedAccount != null) {
				return authenticatedAccount;
			} else {
				throw new UserNotFoundException("unauthorized");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(handler.handleUserException(except));
		}
	}

	@PostMapping("/register")
	public User registerAccount(@RequestBody AccountDTO accountDTO) {
		return  accountService.saveAccount(accountDTO);
	}

	@CrossOrigin(origins = "http://localhost:4200/reset-password", allowedHeaders = {})
	@PutMapping("/updateAccount")
	public AccountDTO updateAccount(@RequestBody AccountDTO accountDTO, @RequestHeader("Authentication") String token) {
		return accountService.updateAccount(accountDTO);
	}

	@DeleteMapping("/deleteAccount")
	public void deleteAccount(@RequestBody AccountDTO accountDTO) {
		System.out.println(accountDTO.toString());
		accountService.deleteAccount(accountDTO);
	}

}

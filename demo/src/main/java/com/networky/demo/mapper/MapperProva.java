package com.networky.demo.mapper;

import com.networky.demo.dtos.AccountDTO;
import com.networky.demo.dtos.UserDTO;
import com.networky.demo.entities.Account;
import com.networky.demo.entities.User;

//@Mapper(componentModel = "spring")
public interface MapperProva {
	
//	public Account toAccount(AccountDTO account);
	
	public AccountDTO toAccountDTO(Account account);
	
	public UserDTO toUserDTO(User user);
	
	public Account dtoToAccount(AccountDTO account);
	
	public User DtoToUser(UserDTO userDTO);
	
}

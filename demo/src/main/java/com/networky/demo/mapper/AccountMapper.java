package com.networky.demo.mapper;

import org.mapstruct.Mapper;

import com.networky.demo.dtos.AccountDTO;
import com.networky.demo.entities.Account;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface AccountMapper {
	
//	AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

	public Account dtoToAccountEntity(AccountDTO account);
	
	public AccountDTO entityToAccountDTO(Account account);
}

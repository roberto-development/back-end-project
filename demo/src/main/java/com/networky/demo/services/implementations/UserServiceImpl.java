package com.networky.demo.services.implementations;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.networky.demo.dao.LoginDAO;
import com.networky.demo.dao.UserDAO;
import com.networky.demo.dtos.AccountDTO;
import com.networky.demo.dtos.UserDTO;
import com.networky.demo.entities.Account;
import com.networky.demo.entities.User;
import com.networky.demo.exceptions.UserNotFoundException;
import com.networky.demo.mapper.AccountMapper;
import com.networky.demo.mapper.UserMapper;
import com.networky.demo.services.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserDAO userDAO;
	
	private LoginDAO loginDAO;
	
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
//	mapper / mapstruct
	private AccountMapper accountMapper = Mappers.getMapper(AccountMapper.class);
	
	private UserMapper userMapper = Mappers.getMapper(UserMapper.class);
	
	@Autowired
	public UserServiceImpl(UserDAO userDAO, LoginDAO loginDAO) {
		this.userDAO = userDAO;
		this.loginDAO= loginDAO;
	}

	@Override
	@Transactional
	public UserDTO getUser(AccountDTO accountDTO) {
		Account dtoToEntity = accountMapper.dtoToAccountEntity(accountDTO);
		
		String email = dtoToEntity.getEmail();
		Account userDb = loginDAO.findByEmail(email);
		if(userDb != null) {
			boolean isPasswordMatch = encoder.matches(dtoToEntity.getPassword(), userDb.getPassword());
			if(isPasswordMatch) {
				UserDTO userDTO = userMapper.entityToUserDTO(userDb.getUser());
				return userDTO;
			} else {
				throw new UserNotFoundException("bad credentials");
			}
		} else {
			throw new UserNotFoundException("error");
		}
	}

	@Override
	@Transactional
	public UserDTO updateUser(UserDTO userDTO) {
		User userEntity = userMapper.newFieldsToEntity(userDTO);
		User userToUpdate = userDAO.save(userEntity);
		UserDTO userUpdated = userMapper.entityToUserDTO(userToUpdate);
		return userUpdated;
	}

}


package com.networky.demo.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.networky.demo.daos.interfaces.UserDAO;
import com.networky.demo.dtos.AccountDTO;
import com.networky.demo.dtos.UserDTO;
import com.networky.demo.entities.Account;
import com.networky.demo.entities.User;
import com.networky.demo.exceptions.UserNotFoundException;
import com.networky.demo.mapper.MapperProvaImpl;
import com.networky.demo.services.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserDAO userDAO;
	
	private MapperProvaImpl mapperProva = new MapperProvaImpl();

	@Autowired
	public UserServiceImpl(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	@Transactional
	public User saveUser(AccountDTO account) {
		Account saveAccount = mapperProva.dtoToAccount(account);
		return userDAO.saveUser(saveAccount);
	}


	@Override
	@Transactional
	public UserDTO getUser(Account account) {

		Account accountDatabase = userDAO.getUser(account);	
		if(accountDatabase != null) {

			User userFromAccountDatabase = accountDatabase.getUser();

			UserDTO newUserDTO = mapperProva.toUserDTO(userFromAccountDatabase);
			return newUserDTO;
		} else {
			throw new UserNotFoundException("bad credentials");
		}
	}


//	@Override
//	@Transactional
//	public AccountDTO updateAccount(AccountDTO account) {
//
//		AccountDTO result = null ;
//
//		Account theAccount = new Account();
//		theAccount.setEmail(account.getEmail());
//		theAccount.setPassword(account.getPassword());
//
//		Account theQueryAccount = userDAO.getUser(theAccount); 
//
//		if(account.getNewEmail() != null) {
//
//			theQueryAccount.setEmail(account.getNewEmail());
//
//		}
//
//		if(account.getNewPassword() != null) {
//
//			theQueryAccount.setPassword(account.getNewPassword());
//			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//			theQueryAccount.setPassword(encoder.encode(theQueryAccount.getPassword()));
//		}
//
//		Account theUpdatedAccount = userDAO.updateAccount(theQueryAccount);
//
//		result = mapperProva.toAccountDTO(theUpdatedAccount);
//
//
//
//		return result;
//
//	}

	@Override
	@Transactional
	public UserDTO updateUser(AccountDTO account) {
		
//		login
		Account theAccount = new Account();
		theAccount.setEmail(account.getEmail());
		theAccount.setPassword(account.getPassword());
		Account theQueryAccount = userDAO.getUser(theAccount);
		
//		imposto i nuovi dati che mando da postman
		theQueryAccount.getUser().setId(theQueryAccount.getUser().getId());
		theQueryAccount.getUser().setNome(account.getUserDTO().getNewNome());
		theQueryAccount.getUser().setCognome(account.getUserDTO().getNewCognome());
		theQueryAccount.getUser().setDataDiNascita(account.getUserDTO().getNewDataDiNascita());
		theQueryAccount.getUser().setCountry(account.getUserDTO().getNewCountry());
		userDAO.updateUser(theQueryAccount);
		
//		nuovo oggetto con user aggiornato
		User userAggiornato = setterUser(theQueryAccount.getUser());	
		
		UserDTO userUpdated = mapperProva.toUserDTO(userAggiornato);
		return userUpdated;

	}
	
	public User setterUser(User user) {
		User userDaAggiornare = new User();
		userDaAggiornare.setId(user.getId());
		userDaAggiornare.setNome(user.getNome());
		userDaAggiornare.setCognome(user.getCognome());
		userDaAggiornare.setDataDiNascita(user.getDataDiNascita());
		userDaAggiornare.setCountry(user.getCountry());
		
		return userDaAggiornare;
	}

}

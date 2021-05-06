package com.networky.demo.services.implementations;

import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.networky.demo.dtos.AccountDTO;
import com.networky.demo.dtos.TokenDTO;
import com.networky.demo.dtos.UserDTO;
import com.networky.demo.entities.Account;
import com.networky.demo.entities.User;
import com.networky.demo.exceptions.UserNotFoundException;
import com.networky.demo.mapper.AccountMapper;
import com.networky.demo.mapper.UserMapper;
import com.networky.demo.repository.AccountRepository;
import com.networky.demo.repository.LoginRepository;
import com.networky.demo.services.interfaces.AccountService;
import com.networky.demo.util.JwtUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class AccountServiceImpl implements AccountService {
	
	
	private final AccountRepository accountDAO;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private LoginRepository loginDAO;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	HttpHeaders headers = new HttpHeaders();
	
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	private AccountMapper accountMapper = Mappers.getMapper(AccountMapper.class);
	
	private UserMapper userMapper = Mappers.getMapper(UserMapper.class);
	
	
	@Autowired
	public AccountServiceImpl(AccountRepository accountDAO) {
		this.accountDAO = accountDAO;
	}
	
	public Session getSession() {
		return entityManager.unwrap(Session.class);
	}
	
	@Override
	@Transactional
	public ResponseEntity<TokenDTO> login(AccountDTO accountDTO) {		
		TokenDTO tokenDTO = new TokenDTO();
		String email = accountDTO.getEmail();
		Account userDb = loginDAO.findByEmail(email);
		if(userDb != null) {
			boolean isPasswordMatch = encoder.matches(accountDTO.getPassword(), userDb.getPassword());
			if(isPasswordMatch) {
				UserDTO userDTO = userMapper.entityToUserDTO(userDb.getUser());
				System.out.println(userDTO.toString());
				
				HashMap<String, Object> addedValues = new HashMap<String, Object>();
				addedValues.put("id", userDTO.getId());
				tokenDTO = jwtUtils.generateToken(addedValues);
				System.out.println(tokenDTO.toString());
				return ResponseEntity.ok().body(tokenDTO);
			} else {
				throw new UserNotFoundException("bad credentials");
			}
		} else {
			throw new UserNotFoundException("error");
		}
	}
	

	public HttpHeaders getHeader(String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authentication", "Bearer" + token);
		return headers;
	}
	
	@Override
	@Transactional
	public Account getAccount(AccountDTO account) {
		String email = account.getEmail();
		Account findAccount = null;
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Query<Account> theQuery = getSession().createQuery("from Account where email= :email", Account.class)
				.setParameter("email", email);
		findAccount = theQuery.uniqueResult();
		if(findAccount != null) {
			
			boolean isPasswordMatch = encoder.matches(account.getPassword(), findAccount.getPassword());

			if(isPasswordMatch) {
//				return findAccount;
			} else {
				return null;
			}
		}
			return findAccount;
		
	}
	
	@Override
	@Transactional
	public User saveAccount( AccountDTO accountDTO ) {
		
		Account saveEntityAccount = mapperEntity( accountDTO );
		accountDAO.save( saveEntityAccount );
		User entityUser = saveEntityAccount.getUser();
		return entityUser;
	}


	@Override
	@Transactional
	public AccountDTO updateAccount(AccountDTO accountDTO) {
		AccountDTO result = null;
		AccountDTO loginAccount = new AccountDTO();
		loginAccount.setEmail(accountDTO.getEmail());
		loginAccount.setPassword(accountDTO.getPassword());
		Account loggedAccount = getAccount(loginAccount);
		if(loggedAccount == null) {
			throw new UserNotFoundException("USER NOT FOUND");
//			return null;
		}
		Account theQueryAccount = new Account(); 

//		 è meglio creare entity per salvare new mail/pw o gestire diversamente questo metodo?
		
		if(accountDTO.getNewEmail() != null) {
			theQueryAccount.setId(loggedAccount.getId());
			theQueryAccount.setEmail(accountDTO.getNewEmail());
		}
		if(accountDTO.getNewPassword() != null) {
			theQueryAccount.setPassword(accountDTO.getNewPassword());
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			theQueryAccount.setPassword(encoder.encode(theQueryAccount.getPassword()));
		} else {
			theQueryAccount.setPassword(accountDTO.getPassword());
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			theQueryAccount.setPassword(encoder.encode(theQueryAccount.getPassword()));
		}
		theQueryAccount.setUser(loggedAccount.getUser());
		accountDAO.save(theQueryAccount);
		result = accountMapper.entityToAccountDTO(theQueryAccount);
		result.setUserDTO(userMapper.entityToUserDTO(theQueryAccount.getUser()));
		return result;

	}


	@Override
	@Transactional
	public void deleteAccount(AccountDTO accountDTO) {
		Account newEntityAccount = accountMapper.dtoToAccountEntity(accountDTO);
		accountDAO.delete(newEntityAccount);		
	}
	
	protected Account setEmailPasswordAccount(AccountDTO accountDTO) {
		Account newEntityAccount = new Account();
		newEntityAccount.setEmail(accountDTO.getEmail());
		newEntityAccount.setPassword(accountDTO.getPassword());
		return newEntityAccount;
	}
	
	protected AccountDTO setEmailPasswordAccountDTO(AccountDTO accountDTO) {
		AccountDTO newEntityAccountDTO = new AccountDTO();
		newEntityAccountDTO.setEmail(accountDTO.getEmail());
		newEntityAccountDTO.setPassword(accountDTO.getPassword());
		return newEntityAccountDTO;
	}
	
	protected Account mapperEntity(AccountDTO accountDTO) {
		Account saveEntityAccount = accountMapper.dtoToAccountEntity( accountDTO );
		User userEntityFromAccount = userMapper.DtoToEntityUser( accountDTO.getUserDTO() );
		saveEntityAccount.setUser(userEntityFromAccount);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		saveEntityAccount.setPassword( encoder.encode( accountDTO.getPassword()) );	
		return saveEntityAccount;
	}
	
	
	

}

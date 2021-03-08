package com.networky.demo.services.implementations;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.networky.demo.dao.AccountDAO;
import com.networky.demo.dtos.AccountDTO;
import com.networky.demo.entities.Account;
import com.networky.demo.entities.User;
import com.networky.demo.mapper.AccountMapper;
import com.networky.demo.mapper.UserMapper;
import com.networky.demo.services.interfaces.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	private final AccountDAO accountDAO;
	
	@Autowired
	private EntityManager entityManager;
	
	private AccountMapper mapper = Mappers.getMapper(AccountMapper.class);
	
	private UserMapper userMapper = Mappers.getMapper(UserMapper.class);
	
	
	@Autowired
	public AccountServiceImpl(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}
	
	public Session getSession() {
		return entityManager.unwrap(Session.class);
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
			return null;
		}
		Account theQueryAccount = new Account(); 

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
		result = mapper.entityToAccountDTO(theQueryAccount);
		result.setUserDTO(userMapper.entityToUserDTO(theQueryAccount.getUser()));
		return result;

	}


	@Override
	@Transactional
	public void deleteAccount(AccountDTO accountDTO) {
		Account newEntityAccount = mapper.dtoToAccountEntity(accountDTO);
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
		Account saveEntityAccount = mapper.dtoToAccountEntity( accountDTO );
		User userEntityFromAccount = userMapper.DtoToEntityUser( accountDTO.getUserDTO() );
		saveEntityAccount.setUser(userEntityFromAccount);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		saveEntityAccount.setPassword( encoder.encode( accountDTO.getPassword()) );	
		return saveEntityAccount;
	}
	
	
	

}

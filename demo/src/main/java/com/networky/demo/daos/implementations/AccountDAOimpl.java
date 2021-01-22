package com.networky.demo.daos.implementations;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.networky.demo.daos.interfaces.AccountDAO;
import com.networky.demo.entities.Account;

@Repository
public class AccountDAOimpl implements AccountDAO {
	
	private final EntityManager entityManager;

	@Autowired
	public AccountDAOimpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Account updateAccount(Account account) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(account);
		return account;
	}

}

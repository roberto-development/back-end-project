package com.networky.demo.test;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.networky.demo.dtos.AccountDTO;
import com.networky.demo.entities.Account;
import com.networky.demo.entities.User;

@Repository()
//@EnableJpaRepositories
public interface UserRepository extends JpaRepository<Account, Integer>, CrudRepository<Account, Integer> {
	
	public List<Account> findByEmail(String email);
	
//	@Query("SELECT a FROM Account a WHERE a.email = :email")
//	public List<Account> findByEmailQuery(@Param(value="email") String email);
	
//	public List<Account> saveAccount(Account account);
	
//	test save user method
	
//	@Query("INSERT INTO Account a (id, email, password, id_user) VALUES (?,?,?,?)")
//	public void saveAccountById(@Param(value="a") AccountDTO accountDto);
}

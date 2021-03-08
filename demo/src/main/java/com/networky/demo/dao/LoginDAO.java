package com.networky.demo.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.networky.demo.entities.Account;

@Repository
public interface LoginDAO extends JpaRepository<Account, Integer> {

	@Query(value = "SELECT a FROM Account a WHERE a.email= :email")
	public Account findByEmail(@Param(value="email") String email);
	
//	public Account findAllByEmail(String email);
	
}

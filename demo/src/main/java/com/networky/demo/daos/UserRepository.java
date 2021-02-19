package com.networky.demo.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.networky.demo.entities.Account;
import com.networky.demo.entities.User;

@Repository("userRepo")
//@EnableJpaRepositories
public interface UserRepository extends JpaRepository<Account, Integer>, CrudRepository<Account, Integer> {
	
	/* 
	 *find account by email and return proper user 
	 */
//	public final static String FIND_ACCOUNT_BY_EMAIL = "SELECT a " + "FROM Account a " + "WHERE a.email = :email";

//	public Account findOne(Account email);
	
	public List<Account> findByEmail(String email);
	
	@Query("SELECT a FROM Account a WHERE a.email = :email")
	public List<Account> findByEmailQuery(@Param(value="email") String email);
	

	public List<Account> saveUser(Account account);
	
}

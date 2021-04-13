package com.networky.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.networky.demo.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	 @Query(value = "SELECT u FROM User u WHERE u.id= :id")
	public User findUserById(@Param(value="id") Integer id);
}

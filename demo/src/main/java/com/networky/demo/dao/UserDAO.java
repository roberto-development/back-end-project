package com.networky.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.networky.demo.entities.User;

public interface UserDAO extends JpaRepository<User, Integer> {

}

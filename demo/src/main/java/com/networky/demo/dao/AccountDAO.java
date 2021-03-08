package com.networky.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.networky.demo.entities.Account;

public interface AccountDAO extends JpaRepository<Account, Integer> {

}

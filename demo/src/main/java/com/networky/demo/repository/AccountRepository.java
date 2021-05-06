package com.networky.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.networky.demo.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}

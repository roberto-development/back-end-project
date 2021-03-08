package com.networky.demo.test;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.networky.demo.dtos.AccountDTO;
import com.networky.demo.entities.Account;
import com.networky.demo.entities.User;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins ="*" , allowedHeaders = "*" )
//@EntityScan( basePackages = {"entities"})
public class TestController {
	
	@Autowired
	private TestService testService;
	
    @GetMapping("")
    public String confirmFunctionalityOfTheApp () {
        return "The Application is running correctly, time on server is : " + LocalDateTime.now() ;
    }
    
//    @PostMapping("/getUser")
//    public ResponseEntity<List<Account>> getUserByEmail(@RequestBody AccountDTO accountDto) {
//    	return new ResponseEntity<>(testService.getUserByEmail(accountDto.getEmail()), HttpStatus.OK);
//    }
    
//    @PostMapping("/saveUser")
//    public User saveUser(@RequestBody Account accountDto) {
//    	testService.saveAccount(accountDto);
//    }

}
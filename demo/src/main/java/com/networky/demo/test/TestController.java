package com.networky.demo.test;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {
    @GetMapping("")
    public String confirmFunctionalityOfTheApp () {
        return "The Application is running correctly, time on server is : " + LocalDateTime.now() ;
    }

}
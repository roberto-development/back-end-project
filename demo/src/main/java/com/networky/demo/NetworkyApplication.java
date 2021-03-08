package com.networky.demo;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.networky.demo.dtos.PostDTO;
import com.networky.demo.entities.Post;
import com.networky.demo.mapper.PostMapper;

@SpringBootApplication( exclude = { SecurityAutoConfiguration.class })
public class NetworkyApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(NetworkyApplication.class, args);
	}
	
//	@Bean
//	   public WebMvcConfigurer corsConfigurer() {
//	       return new WebMvcConfigurer() {
//	           @Override
//	           public void addCorsMappings(CorsRegistry registry) {
//	               registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*").exposedHeaders("Authorization");
//	           }
//	       };
//	   }

}

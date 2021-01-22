package com.networky.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

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

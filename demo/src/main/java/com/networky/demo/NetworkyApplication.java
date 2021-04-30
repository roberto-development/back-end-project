package com.networky.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// (exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication
public class NetworkyApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(NetworkyApplication.class, args);
		
	}
	
//	@Bean
//	public TokenFilterAuthentication filter() {
//	     return new TokenFilterAuthentication();
//	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**") 
						.allowedOrigins("*")
						.allowedMethods("*")
						.allowedHeaders("*")
						.exposedHeaders("Authentication");
			}
		};
	}
	
//	@Bean
//	   public WebMvcConfigurer corsConfigurer() {
//	       return new WebMvcConfigurer() {
//	           @Override
//	           public void addCorsMappings(CorsRegistry registry) {
//	               registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*").exposedHeaders("Authentication");
//	           }
//	       };
//	   }

	
	
}

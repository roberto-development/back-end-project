package com.networky.demo.dtos;

import org.springframework.http.HttpHeaders;

public class TokenDTO {
	


	private long expiration;
	
	private String token;
	
	public TokenDTO() {
	}
	
	
	public TokenDTO(long expiration, String token) {
		this.expiration = expiration;
		this.token = token;
	}


	public long getExpiration() {
		return expiration;
	}

	public void setExpiration(long expiration) {
		this.expiration = expiration;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "TokenDTO [expiration=" + expiration + ", token=" + token + "]";
	}
}

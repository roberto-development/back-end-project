package com.networky.demo.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.networky.demo.dtos.TokenDTO;
import com.networky.demo.mapper.UserMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtUtils implements Serializable {
	

	private String SIGNATURE = "networky";

	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	
	private String tokenExpiration;
	
	TokenDTO newTokenDTO = new TokenDTO();
	
	@Autowired
	private UserMapper userMapper;
	
//	public String generateToken(UserDTO userDTO) {
//		Map<String, Object> claims = new HashMap<>();
//		claims.put(CLAIM_KEY_AUDIENCE, claims)
//	}
	
	public String getIdFromToken(HttpServletRequest httpRequest) {
		System.out.println("get Id from token : " + httpRequest);
        String email;
        try {
            final Claims claims = getClaimsFromToken(httpRequest.getHeader("Authentication"));
            email = claims.getSubject();
            System.out.println(email);
        } catch (Exception e) {
            email = null;
        }
        return email;
    }
	
	   public Date getExpirationDateFromToken(String token) {
	        Date expiration;
	        try {
	            final Claims claims = getClaimsFromToken(token);
	            expiration = claims.getExpiration();
	        } catch (Exception e) {
	            expiration = null;
	        }
	        return expiration;
	    }
	   
//	public String callingMethodToken(HashMap<String, Object> addedValues2) throws JsonProcessingException {
//		HashMap<String, Object> addedValues = new HashMap<String, Object>();
//		addedValues.put("data", addedValues2);
//		return generateToken(addedValues);
//	}
//	
//	private Date generateExpirationDate() {
//		Date exp = "1000000"; 
//		return exp;
////        return new Date(System.currentTimeMillis() + expiration * 1000);
//    }
	
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
	
    public TokenDTO generateToken(HashMap<String, Object> claims) {
    	Date expirationDate = new Date(System.currentTimeMillis() + 20000); //  * 60 * 24000
    	String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, "networky")
                .compact();
    	System.out.println(token);
    	newTokenDTO.setToken(token);
    	newTokenDTO.setExpiration(System.currentTimeMillis() + 20000 * 20);
    	return newTokenDTO;
//        ObjectMapper mapper = new ObjectMapper();
//        return 
    }
	
	public Claims getClaimsFromToken(String httpRequest) {
		System.out.println("get Claims from token : " + httpRequest);
		Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey("networky")
                    .parseClaimsJwt(httpRequest)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }
	
}

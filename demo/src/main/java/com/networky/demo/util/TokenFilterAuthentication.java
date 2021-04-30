package com.networky.demo.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networky.demo.exceptions.UserNotFoundException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class TokenFilterAuthentication extends OncePerRequestFilter {
	
//	private final String HEADER = "Authentication";
//	private final String PREFIX = "Bearer ";
//	private final String SECRET = "networky";
	
//	before: private static final
	 private Logger logger = LoggerFactory.getLogger(TokenFilterAuthentication.class);

	    @Autowired
	    private JwtUtils jwtTokenUtil;
	    
	    @Override
		protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
				throws IOException, UserNotFoundException, ServletException {
			
	    	System.out.println("ciao sono filter");
	    	
	    	
	    	// ignore OPTION method calls
	    	if (!HttpMethod.OPTIONS.name().equalsIgnoreCase(request.getMethod()) && !("/api/login".equalsIgnoreCase(request.getRequestURI())
	    			|| "/api/register".equalsIgnoreCase(request.getRequestURI()))) {
	   
	    		String token = request.getHeader("Authentication");
	    		if( token == null || !token.startsWith("Bearer") ) {
	    			System.out.println("--------Token not found");
	    			setErrorOnHttpResponse(response, HttpStatus.UNAUTHORIZED, "TOKEN NOT FOUND");
	    			return;
	    		}
	    	
			String tokenHeader = token.replace("Bearer ", "");
			
			try {
				Jwts.parser().setSigningKey("networky").parseClaimsJws(tokenHeader).getBody();
				System.out.println("ciao sto verificando il token");
				
				chain.doFilter(request, response);
			} catch (ExpiredJwtException ex) {
				System.out.println("Token scaduto");
				request.setAttribute("expired", ex.getMessage());
				final String expiredMsg = (String) request.getAttribute("expired");
				final String msg = (expiredMsg != null) ? expiredMsg : "Unauthorized";
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, msg);
				
//				setErrorOnHttpResponse(response, HttpStatus.UNAUTHORIZED, msg);
				return;
			}
			} else {
				chain.doFilter(request, response);
			}
		}
	    
		private void setErrorOnHttpResponse(HttpServletResponse httpResponse, HttpStatus status, String result)
				throws IOException, JsonProcessingException {
			
			httpResponse.setStatus(status.value());
			httpResponse.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
			httpResponse.getOutputStream().print(new ObjectMapper().writeValueAsString(result));
		}
	    
}




//	    
//		public String buildTokenInternal(UserDTO userDTO) throws Exception {
//			
//			String encryptedResponse;
//			HashMap<String, Object> addedValues = new HashMap<String, Object>();
//			addedValues.put("data", userDTO.getId());
//			}


//	    public TokenFilterAuthentication(AuthenticationManager authenticationManager) {
//		super(authenticationManager);
//		// TODO Auto-generated constructor stub
//	}
//		
//	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//	
//	            throws IOException, ServletException
//	
//	    {
//	
//	        String header = request.getHeader("Authentication");
////	        header.isEmpty();
//	
//	        if(header.isEmpty() || header.startsWith("Bearer"))
//	        	
//	        {
//	            filterChain.doFilter(request,response);
//	
//	        }
//	
////	        gestire OPTION
////	        controllo metodo authent., per gli altri metodi dovrà esserci 
//	
//	        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);
//	
//	        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//	
//	        filterChain.doFilter(request,response);
//	
//	    }
//	    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request)
//	    {
//	        String token = request.getHeader("Authentication");
//	        if(token != null)
//	        {
//	            String user = Jwts.parser().setSigningKey("SecretKeyToGenJWTs".getBytes())
//	                    .parseClaimsJws(token.replace("Bearer",""))
//	                    .getBody()
//	                    .getSubject();
//	            if(user != null)
//	            {
//	                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
//	
//	            }
//	
//	            return null;
//	
//	        }
//	
//	        return null;
//	
//	    }
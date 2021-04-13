package com.networky.demo.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import com.networky.demo.exceptions.UserNotFoundException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

public class TokenFilterAuthentication extends OncePerRequestFilter {
	
	 private static final Logger logger = LoggerFactory.getLogger(TokenFilterAuthentication.class);
//
	    @Autowired
	    private JwtUtils jwtTokenUtil;
	    @Override
		protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
				throws IOException, UserNotFoundException, ServletException {
			// ignore OPTION method calls
			if (HttpMethod.OPTIONS.name().equalsIgnoreCase(request.getMethod())) {
				chain.doFilter(request, response);
				return;
			}

			// ignore all permitted routes
			if (request.getRequestURI().startsWith("/api") || "/api/login".equalsIgnoreCase(request.getRequestURI())
					|| "/api/register".equalsIgnoreCase(request.getRequestURI())
					|| "/api/profile".equalsIgnoreCase(request.getRequestURI())
					|| request.getRequestURI().startsWith("/post")) {
				chain.doFilter(request, response);
//				return;
			}

			String header = request.getHeader("Authentication");
			if (header.isEmpty() || !header.startsWith("Bearer ")) {
				throw new UserNotFoundException("token not found");
			}
			String token = header.replace("Bearer ", "");
			try {
				Jwts.parser().setSigningKey("Authentication").parseClaimsJws(token).getBody();
			} catch (ExpiredJwtException ex) {
				request.setAttribute("expired", ex.getMessage());
				final String expiredMsg = (String) request.getAttribute("expired");
				final String msg = (expiredMsg != null) ? expiredMsg : "Unauthorized";
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, msg);
			}
			chain.doFilter(request, response);
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
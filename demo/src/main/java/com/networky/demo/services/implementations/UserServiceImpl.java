package com.networky.demo.services.implementations;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.networky.demo.dtos.AccountDTO;
import com.networky.demo.dtos.TokenDTO;
import com.networky.demo.dtos.UserDTO;
import com.networky.demo.entities.Account;
import com.networky.demo.entities.User;
import com.networky.demo.exceptions.UserNotFoundException;
import com.networky.demo.mapper.AccountMapper;
import com.networky.demo.mapper.UserMapper;
import com.networky.demo.repository.LoginRepository;
import com.networky.demo.repository.UserRepository;
import com.networky.demo.services.interfaces.UserService;
import com.networky.demo.util.JwtUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	private String SIGNATURE = "networky";

	private final UserRepository userDAO;
	
	private LoginRepository loginDAO;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
//	mapper / mapstruct
	private AccountMapper accountMapper = Mappers.getMapper(AccountMapper.class);
	
	private UserMapper userMapper = Mappers.getMapper(UserMapper.class);
	
	@Autowired
	public UserServiceImpl(UserRepository userDAO, LoginRepository loginDAO, JwtUtils jwtUtils) {
		this.userDAO = userDAO;
		this.loginDAO = loginDAO;
		this.jwtUtils = jwtUtils;
	}
	
	@Override
	public UserDTO getUserInfo(HttpServletRequest httpRequest) {
		String decodeBearer = httpRequest.getHeader("Authentication");
		UserDTO userDTO = null;
		String decodeToken = decodeBearer.replace("Bearer ", "");
		
			
		Integer idAccount = (Integer) Jwts.parser().setSigningKey(SIGNATURE).parseClaimsJws(decodeToken).getBody().get("id");
		System.out.println(idAccount);
		User findUser = userDAO.findUserById(idAccount);//		
//		System.out.println("findUser : " + findUser.toString()); non funziona => stack overflow
		System.out.println("findUser id :" + findUser.getId()); 
		userDTO = userMapper.entityToUserDTO(findUser);
		System.out.println("userDTO id :" + userDTO.getId());
		return userDTO;
		
	}
	

//	@Override
//	@Transactional
//	public UserDTO getUser(AccountDTO accountDTO) {		// public ResponseEntity<UserDTO>
//		Account dtoToEntity = accountMapper.dtoToAccountEntity(accountDTO);
//		
//		String email = dtoToEntity.getEmail();
//		Account userDb = loginDAO.findByEmail(email);
//		if(userDb != null) {
//			boolean isPasswordMatch = encoder.matches(dtoToEntity.getPassword(), userDb.getPassword());
//			if(isPasswordMatch) {
//				UserDTO userDTO = userMapper.entityToUserDTO(userDb.getUser());
//				/* HttpHeaders headers = new HttpHeaders();
//				 * HashMap<String, Object> addedValues = new HashMap<String, Object>();
//				 * addedValues.put("id", userDTO.getId());
//				 * String toker = Jwts.builder()
//				 * .addClaims(addedValues)
//				 * .setIssuedAt(new DateSystem.currentTimeMillis()))
//				 * .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24000))
//				 * .signWith(SignatureAlgorithm.HS512, "scaiconsulting").compact();
//				 * headers.add("Authentication", "Bearer" + token);
//				 * return ResponseEntity.ok().headers(headers).body(userDTO);
//
//				 * 
//				 */
//				return userDTO;
//			} else {
//				throw new UserNotFoundException("bad credentials");
//			}
//		} else {
//			throw new UserNotFoundException("error");
//		}
//	}
	
	
//	metodo non implementato
	@Override
	public ResponseEntity<TokenDTO> getProfileUser(AccountDTO accountDTO) {
//		Account dtoToEntity = accountMapper.dtoToAccountEntity(accountDTO);
//		TokenDTO tokenDTO = new TokenDTO();
//		String email = accountDTO.getEmail();
//		Account userDb = loginDAO.findByEmail(email);
//		
//		if(userDb != null) {
//			boolean isPasswordMatch = encoder.matches(accountDTO.getPassword(), userDb.getPassword());
//			if(isPasswordMatch) {
//				
//				UserDTO userDTO = userMapper.entityToUserDTO(userDb.getUser());
//				HashMap<String, Object> addedValues = new HashMap<String, Object>();
//				addedValues.put("id", userDTO.getId());
//				
//				String token = Jwts.builder()
//				.addClaims(addedValues)
////				.setIssuedAt(new Date(System.currentTimeMillis()))
//				.setExpiration(new Date(System.currentTimeMillis() + 864000000))
//				.signWith(SignatureAlgorithm.HS512, SIGNATURE.getBytes())
//				.compact();
//				System.out.println("tokenDTO" + tokenDTO.toString());
////				return ResponseEntity.ok().headers(headers).body(userDTO);
//				return ResponseEntity.ok().body(tokenDTO);
		return null;
//			} else {
//				throw new UserNotFoundException("bad credentials");
//			}
//		} else {
//			throw new UserNotFoundException("error");
//		}
	}
		
	

	@Override
	public UserDTO updateUser(UserDTO userDTO) {
		User userEntity = userMapper.newFieldsToEntity(userDTO);
		User userToUpdate = userDAO.save(userEntity);
		UserDTO userUpdated = userMapper.entityToUserDTO(userToUpdate);
		return userUpdated;
	}
	
	@Override
	public List<User> listAll() {
		return userDAO.findAll(Sort.by("id").ascending());
	}
}


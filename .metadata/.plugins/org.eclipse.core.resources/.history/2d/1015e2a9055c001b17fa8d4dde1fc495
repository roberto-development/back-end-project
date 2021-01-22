package com.networky.demo.daos.implementations;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.networky.demo.daos.interfaces.UserDAO;
import com.networky.demo.entities.Account;
import com.networky.demo.entities.User;

@Repository
public class UserDAOimpl implements UserDAO {


	private final EntityManager entityManager;

	@Autowired
	public UserDAOimpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}



	@Override
	public Account getUser(Account account) {
		Session currentSession = entityManager.unwrap(Session.class);
		Account findAccount = null;
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Query<Account> theQuery = currentSession.createQuery("from Account where email= :email", Account.class)
				.setParameter("email", account.getEmail());
		findAccount = theQuery.uniqueResult();
		if(findAccount != null) {

			boolean isPasswordMatch = encoder.matches(account.getPassword(), findAccount.getPassword());

			if(isPasswordMatch) {
				return findAccount;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}


	@Override
	public User saveUser(Account account) {		
		System.out.println("\nAccount dentro userDAO: " + account.toString() + account.getUser().toString());

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Session currentSession = entityManager.unwrap(Session.class);
		account.setPassword(encoder.encode(account.getPassword()));	
		// image ha user, account ha user
		
		currentSession.saveOrUpdate(account);

		return account.getUser();
	}
	
	// piccolo metodo per getUser
//	public Account getAccount(String email, String password) {
//		Session currentSession = entityManager.unwrap(Session.class);
//		Account findAccount = null;
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		Query<Account> theQuery = currentSession.createQuery("from Account where email= :email and password= :password", Account.class)
//				.setParameter("email", email).setParameter("password", encoder.encode(password));
//		
//	}

//		@Override
//		public Account updateEmail(Account account) {
//			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//			System.out.println(account.toString());
//			Session currentSession = entityManager.unwrap(Session.class);
//			System.out.println("\ndentro updateMail" + account.toString());
//			currentSession.saveOrUpdate(account);
//			
//			return account;
//		}
	//
	//
	//
//		@Override
//		public Account updatePassword(Account account) {
//			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//			Session currentSession = entityManager.unwrap(Session.class);
//			account.setPassword(encoder.encode(account.getPassword()));		
//			currentSession.saveOrUpdate(account);
//			
//			return account;
//					
//		}



//	@Override
//	public Account updateAccount(Account account) {
//
//		
//		//storedHas = accountCheck.getPassword();
//		Session currentSession = entityManager.unwrap(Session.class);
////		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
////		encoder.matches(accountCheck, encodedPassword);
//		
////		accountCheck.setPassword(encoder.encode(accountCheck.getPassword()));
//		currentSession.saveOrUpdate(account);
//		return account;
//		// 
//
//	}



	@Override
	public User updateUser(Account account) {

		// da qui arriva oggetto user passato da param
		System.out.println("\nUser dentro updateUser : " + account.toString());
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(account);
		
		return account.getUser();
	}



//	@Override
//	public User updateImage(User newUserForImage) {
//		Session currentSession = entityManager.unwrap(Session.class);
//		currentSession.saveOrUpdate(newUserForImage);
//		return newUserForImage;
//	}
	
//	@Override
//	public void saveOrUpdateImage(Image image) {
//		Session currentSession = entityManager.unwrap(Session.class);
//		currentSession.saveOrUpdate(image);
//	}


}

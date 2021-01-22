package com.networky.demo.dtos;

public class AccountDTO {

	private int id;

	private String email;

	private String newEmail;

	private String password;

	private String newPassword;

	private UserDTO userDTO;

	public AccountDTO() {

	}

	public AccountDTO(int id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}

	

	public AccountDTO(String email, String newEmail, String password, String newPassword, UserDTO userDTO) {
		this.email = email;
		this.newEmail = newEmail;
		this.password = password;
		this.newPassword = newPassword;
		this.userDTO = userDTO;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO user) {
		this.userDTO = user;
	}
	
	public String getNewEmail() {
		return newEmail;
	}

	public void setNewEmail(String newEmail) {
		this.newEmail = newEmail;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public String toString() {
		return "AccountDTO [id=" + id + ", email=" + email + ", newEmail=" + newEmail + ", password=" + password
				+ ", newPassword=" + newPassword + ", userDTO=" + userDTO + "]";
	}


}

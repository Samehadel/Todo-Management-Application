package com.todo.application.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="users_tbl")
public class UserEntity {

	@Id
	@GeneratedValue
	long id;
	
	@Column(nullable = false)
	private String userId;
	
	@Column(nullable = false, length = 75)
	private String userName;
	
	@Column(nullable = false, length = 120)
	private String email;
	
	@Column(nullable = false)
	private String encryptedPassword;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	
	
}

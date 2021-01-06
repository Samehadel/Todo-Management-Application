package com.todo.application.data.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@Column(name = "full_name", nullable = false, length = 120)
	private String fullName;
	
	@Column(name = "user_name", nullable = false, length = 150)
	private String userName;
	
	@Column(name = "encrypted_password", nullable = false)
	private String encryptedPassword;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private UserAuthorities authorities;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<TodoEntity> todos;
	
	public UserEntity() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public UserAuthorities getAuthorities() {
		return authorities;
	}

	public void setAuthorities(UserAuthorities authorities) {
		this.authorities = authorities;
	}

	public List<TodoEntity> getTodos() {
		return todos;
	}

	public void setTodos(List<TodoEntity> todos) {
		this.todos = todos;
	}

	public void addTodo(TodoEntity todo) {
		if(todos == null)
			todos = new ArrayList<>();
		
		todos.add(todo);
	}
}

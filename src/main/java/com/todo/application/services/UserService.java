package com.todo.application.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.todo.application.shared.dto.UserDto;

public interface UserService{

	public UserDto createUser(UserDto userDto);
	
}

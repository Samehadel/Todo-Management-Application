package com.todo.application.services;

import com.todo.application.data.entities.UserEntity;
import com.todo.application.shared.dto.UserDto;

public interface UserService{

	public UserDto createUser(UserDto userDto);

	public UserDto retrieveUser(String userName);
	
	public UserEntity retrieveUser(int userId);
		
}

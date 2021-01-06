package com.todo.application.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.todo.application.data.entities.UserAuthorities;
import com.todo.application.data.entities.UserEntity;
import com.todo.application.respository.UserRepository;
import com.todo.application.services.UserService;
import com.todo.application.shared.Utils;
import com.todo.application.shared.dto.UserDto;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	UserRepository userRepo; 

	@Autowired
	BCryptPasswordEncoder encoder; 
	
	@Autowired
	Utils utils;
	
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		UserEntity user = new UserEntity();
		UserAuthorities auth = new UserAuthorities("USER");
		UserDto backDto = new UserDto();
		
		BeanUtils.copyProperties(userDto, user);
		
		// Settings
		user.setEncryptedPassword(encoder.encode(userDto.getPassword()));
		
		auth.setUser(user);
		user.setAuthorities(auth);
		
		//Repo
		UserEntity backEntity = userRepo.save(user);
		
		BeanUtils.copyProperties(backEntity, backDto);
		
		return backDto;
	}

	public UserDto retrieveUser(String userName) {
		UserDto userDto = new UserDto();
		
		UserEntity user = userRepo.findByUserName(userName);
		
		BeanUtils.copyProperties(user, userDto);
		
		return userDto;

	}
	
	public UserEntity retrieveUser(int userId) {
		return userRepo.findById(userId);
	}
	
	/*
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		UserEntity backEntity = userRepo.findByEmail(email);
		
		if(backEntity == null) throw new UsernameNotFoundException(email);
		
		
		return new User(backEntity.getEmail(), backEntity.getEncryptedPassword(), new ArrayList<>());
	}

*/
}

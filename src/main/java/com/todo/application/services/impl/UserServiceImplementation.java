package com.todo.application.services.impl;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
		
		UserEntity entity = new UserEntity();
		UserDto backDto = new UserDto();
		
		BeanUtils.copyProperties(userDto, entity);
		
		entity.setUserId(utils.generateUserId(30));
		entity.setEncryptedPassword(encoder.encode(userDto.getPassword()));
		
		UserEntity backEntity = userRepo.save(entity);
		
		BeanUtils.copyProperties(backEntity, backDto);
		
		return backDto;
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

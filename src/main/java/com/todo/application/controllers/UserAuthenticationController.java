package com.todo.application.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.application.request.models.UserRequestBody;
import com.todo.application.response.models.AuthenticationRest;
import com.todo.application.response.models.RestUser;
import com.todo.application.services.UserService;
import com.todo.application.shared.dto.UserDto;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserAuthenticationController {

	@Autowired
	UserService userService; 
	
	@GetMapping("/users/")
	public AuthenticationRest loginUser(/*@RequestBody UserRequestBody userDetails*/) {
		//System.out.println("Login");
		/*
		userService.loadUserByUsername(userDetails.getEmail());
		*/
		return new AuthenticationRest("Logged In");
		//return userDetails.getUserName();
	}
	
	@PostMapping("/users/")
	public RestUser SignupUser(@RequestBody UserRequestBody userDetails) {
		
		RestUser backRestUser = new RestUser();
		UserDto userDto = new UserDto();
		
		
		BeanUtils.copyProperties(userDetails, userDto);
		
		UserDto backDto = userService.createUser(userDto);
		
		BeanUtils.copyProperties(backDto, backRestUser);
		
		return backRestUser;
	}
}

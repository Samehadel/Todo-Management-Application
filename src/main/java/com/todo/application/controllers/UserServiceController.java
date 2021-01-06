
package com.todo.application.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.application.models.request.UserRequestBody;
import com.todo.application.models.response.RestUser;
import com.todo.application.services.UserService;
import com.todo.application.shared.dto.UserDto;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserServiceController {

	@Autowired
	UserService userService; 
	

	@GetMapping("/users/")
	public void signin() {
		
	}
	
	@PostMapping("/users/login/")
	public RestUser getUser(@RequestBody UserRequestBody user) {
		RestUser backUser = new RestUser();
		UserDto userDto = userService.retrieveUser(user.getUserName());
		
		backUser.setId(userDto.getId());

		return backUser ;
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

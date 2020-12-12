package com.todo.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class TodoApplicationWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoApplicationWebServiceApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("admin"));
	}
	

	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}

}

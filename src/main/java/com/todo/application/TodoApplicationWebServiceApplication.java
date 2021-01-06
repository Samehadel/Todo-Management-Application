package com.todo.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class TodoApplicationWebServiceApplication {

	
	
	public static void main(String[] args) {
		SpringApplication.run(TodoApplicationWebServiceApplication.class, args);
	
	}
	
	
	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}

	
}

package com.todo.application.shared;

import java.security.SecureRandom;

import org.springframework.stereotype.Component;

@Component
public class Utils {
	
	String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQSTUVWXYZabcdefghijklmnopqstuvwxyz";
	SecureRandom rand = new SecureRandom();
	
	public String generateUserId(int length) {
		return generateRandomString(length);
	}

	private String generateRandomString(int length) {
		StringBuilder randomString = new StringBuilder();
		
		for(int i = 0; i < length; i++) {
			randomString.append(ALPHABET.charAt(rand.nextInt(ALPHABET.length())));
		}
		
		return new String(randomString);
	}
}

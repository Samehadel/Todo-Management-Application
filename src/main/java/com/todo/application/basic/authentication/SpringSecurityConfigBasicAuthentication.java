package com.todo.application.basic.authentication;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfigBasicAuthentication extends WebSecurityConfigurerAdapter {

	
	protected void configure(HttpSecurity http) throws Exception {

		//Enable Basic Auth and disable csrf
		http
        .csrf().disable()   
        .authorizeRequests()
        .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
        .anyRequest().authenticated()
        .and().httpBasic();
	}
}

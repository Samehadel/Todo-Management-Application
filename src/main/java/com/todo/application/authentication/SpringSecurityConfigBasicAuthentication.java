package com.todo.application.authentication;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfigBasicAuthentication extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	protected void configure(HttpSecurity http) throws Exception {

		//Enable Basic Auth and disable csrf
		http
        .csrf().disable()   
        .authorizeRequests(auth -> {
        	auth.antMatchers(HttpMethod.POST, "/users/", "/users", "users").permitAll();
        })
        .authorizeRequests()
        .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
        .anyRequest().authenticated()
        	.and().httpBasic().and().formLogin()
        	.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
	}

	 @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth
	                .jdbcAuthentication()
	                .dataSource(dataSource)
	                .passwordEncoder(bCryptPasswordEncoder())
	                .usersByUsernameQuery( "SELECT user_name, encrypted_password, 'true' from users where user_name = ?")
	                .authoritiesByUsernameQuery(
	                        "SELECT u.user_name, a.authority " +
	                        "FROM user_authorities a, users u " +
	                        "WHERE u.user_name = ? " +
	                        "AND u.id = a.user_id"
	                    );
	    }
	
	@Bean 
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

package com.todo.application.respository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.todo.application.data.entities.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long>{

	public UserEntity findByEmail(String email); 
	
}

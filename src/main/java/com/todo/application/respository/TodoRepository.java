package com.todo.application.respository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.todo.application.data.entities.TodoEntity;

@Repository
public interface TodoRepository extends CrudRepository<TodoEntity, Long> {

	public List<TodoEntity> findByUserId(int userId);
	
	public TodoEntity findById(long id);

}

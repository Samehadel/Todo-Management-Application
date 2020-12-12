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

	List<TodoEntity> findAllByUserName(String userName);
	
	TodoEntity findByUserNameAndId(String userName, long id);
	
	@Transactional
	@Modifying
	@Query("delete from todos_tbl todos where todos.userName=:userName and todos.id=:id")
	int deleteByUserNameAndId(@Param("userName") String userName, @Param("id") long id);

	TodoEntity deleteByUserName(String userName);

}

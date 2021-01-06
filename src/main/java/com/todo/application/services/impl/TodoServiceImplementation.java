package com.todo.application.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.application.data.entities.TodoEntity;
import com.todo.application.data.entities.UserEntity;
import com.todo.application.respository.TodoRepository;
import com.todo.application.services.TodoService;
import com.todo.application.services.UserService;
import com.todo.application.shared.dto.TodoDto;

@Service
public class TodoServiceImplementation implements TodoService {

	@Autowired
	TodoRepository todoRepo;

	@Autowired
	UserService userService; 
	
	//Cretae
	@Override
	public void createTodo(TodoDto todoDto) {

		//Create TodoEntity Obj
		TodoEntity todo = new TodoEntity();
		
		//Copy Properties from todoDto to todoEntity
		BeanUtils.copyProperties(todoDto, todo);
		
		//Retrieve Corresponding User
		UserEntity user = userService.retrieveUser(todoDto.getUserId());
		
		//Add the todo to the user
		user.addTodo(todo);
		todo.setUser(user);
		
		//Save todo
		todoRepo.save(todo);
		
	}
	
	//Read
	@Override
	public List<TodoDto> retriveAllTodos(int userId) {

		
		List<TodoEntity> todos = todoRepo.findByUserId(userId);

		List<TodoDto> returnTodos = new ArrayList<>();

		TodoDto todoDto;

		for (TodoEntity todo : todos) {
			todoDto = new TodoDto();
			BeanUtils.copyProperties(todo, todoDto);
			returnTodos.add(todoDto);
		}

		return returnTodos;
	}

	@Override
	public TodoDto retriveTodo(long id) {
		TodoDto todoDto = new TodoDto();
		TodoEntity todo = todoRepo.findById(id);
		
		BeanUtils.copyProperties(todo , todoDto);
		
		
		return todoDto;
	}
	
	//Update
	@Override
	public TodoDto updateTodo(TodoDto todoDto) {
		
		/*TodoEntity todo = todoRepo.updateByUsernameAndId(todoDto.getDescription(), todoDto.getDueDate(), todoDto.getUserName(), todoDto.getId());
		
		todoDto = new TodoDto();
		
		BeanUtils.copyProperties(todo, todoDto);*/
		
		TodoEntity entity = new TodoEntity();
		
		BeanUtils.copyProperties(todoDto, entity);
		
		System.out.println(entity.toString());

		entity = todoRepo.save(entity);
				
		if(entity == null) return null;
				
		return todoDto;
	}


	
	/*
	@Override
	public int deleteTodo(TodoDto todoDto) {

		//int successState = todoRepo.deleteByUserNameAndId(userName, id);

		return 0;
	}*/
/*
	public TodoDto assignId(TodoDto todoDto) {

		List<TodoEntity> todos = todoRepo.findAllByUserName(todoDto.getUserName());

		todoDto.setId(todos.size() + 1);

		return todoDto;
		return null;
		
	}*/
	
	public long getTableId(String userName, long id) {
		//TodoEntity entity = todoRepo.findByUserNameAndId(userName, id);
		
		
		return 0;
	}

	@Override
	public int deleteTodo(TodoDto todoDto) {
		// TODO Auto-generated method stub
		return 0;
	}

}

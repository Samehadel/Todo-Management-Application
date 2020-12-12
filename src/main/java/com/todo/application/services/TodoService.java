package com.todo.application.services;

import java.util.List;

import com.todo.application.shared.dto.TodoDto;

public interface TodoService {

	public int deleteTodo(long id, String userName);

	public TodoDto updateTodo(TodoDto todoDto);
	
	public List<TodoDto> retriveAllTodos(String userName);
	
	public TodoDto retriveTodo(String userName, long id);
	
	public TodoDto createTodo(TodoDto todoDto);
	
	
}

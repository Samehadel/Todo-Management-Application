package com.todo.application.services;

import java.util.List;

import com.todo.application.shared.dto.TodoDto;

public interface TodoService {

	public int deleteTodo(TodoDto todoDto);

	public TodoDto updateTodo(TodoDto todoDto);
	
	public List<TodoDto> retriveAllTodos(int userId);
	
	public TodoDto retriveTodo(long id);
	
	public void createTodo(TodoDto todoDto);
	
	
}

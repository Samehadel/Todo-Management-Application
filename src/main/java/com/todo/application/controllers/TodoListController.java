package com.todo.application.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.application.models.request.TodoRequestBody;
import com.todo.application.models.response.RestTodo;
import com.todo.application.services.TodoService;
import com.todo.application.shared.dto.TodoDto;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TodoListController {

	@Autowired
	TodoService todoService;

	// Create a new todo
	@PostMapping("/users/{userId}/todos/")
	public void createTodo(@PathVariable int userId, @RequestBody TodoRequestBody todoDetails) {

		TodoDto todoDto = new TodoDto();

		BeanUtils.copyProperties(todoDetails, todoDto);

		// Setting some of DTO attributes
		todoDto.setUserId(userId);

		// Use of service
		todoService.createTodo(todoDto);

	}

	// Retrieve user's todos
	@GetMapping("/users/{userId}/todos")
	public List<RestTodo> retrieveTodos(@PathVariable int userId) {

		List<TodoDto> todos = new ArrayList<>();
		List<RestTodo> returnTodos = new ArrayList<>(); // Return object
		RestTodo restTodo = new RestTodo();

		// Use of service
		todos = todoService.retriveAllTodos(userId);

		for (TodoDto todo : todos) {
			BeanUtils.copyProperties(todo, restTodo);

			returnTodos.add(restTodo);
			restTodo = new RestTodo();
		}
		return returnTodos;
	}

	// Retrieve a specific todo
	@GetMapping("/users/{userId}/todos/{id}")
	public RestTodo getTodo(@PathVariable int userId, @PathVariable long id) {

		RestTodo restTodo = new RestTodo(); // Return object

		// Use of service
		TodoDto todoDto = todoService.retriveTodo(id);

		// Copy required attributes of response from DTO (DTO --> Response)
		BeanUtils.copyProperties(todoDto, restTodo);

		return restTodo;
		
	}

	// Update a todo
	@PutMapping("users/{username}/todos/{id}")
	public RestTodo updateTodo(@PathVariable String username, @PathVariable long id,
			@RequestBody TodoRequestBody TodoRequest) {

		TodoDto todoDto = new TodoDto();
		RestTodo restTodo = new RestTodo(); // Return object

		// Copy data from request to DTO (Request --> DTO)
		BeanUtils.copyProperties(TodoRequest, todoDto);

		// Setting some of DTO attributes
		todoDto.setId(id);
		// todoDto.setUserName(username);

		// Use of service
		todoService.updateTodo(todoDto);

		// Copy required attributes of response from DTO (DTO --> Response)
		BeanUtils.copyProperties(todoDto, restTodo);

		return restTodo;
	}

	// Delete a todo
	@DeleteMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id) {

		int sucessState = 1;

		if (sucessState == 1)
			return ResponseEntity.noContent().build();

		else
			return ResponseEntity.notFound().build();
	}
}

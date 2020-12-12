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

import com.todo.application.request.models.TodoRequestBody;
import com.todo.application.response.models.RestTodo;
import com.todo.application.services.TodoService;
import com.todo.application.shared.dto.TodoDto;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TodoListController {

	@Autowired
	TodoService todoService;

	//Edit a todo
	@PutMapping("users/{username}/todos/{id}")
	public RestTodo updateTodo(@PathVariable String username, @PathVariable long id,
							   				@RequestBody TodoRequestBody TodoRequest) {

		TodoDto todoDto = new TodoDto();
		RestTodo restTodo = new RestTodo(); //Return object

		//Copy data from request to DTO (Request --> DTO)
		BeanUtils.copyProperties(TodoRequest, todoDto);

		//Setting some of DTO attributes
		todoDto.setId(id);
		todoDto.setUserName(username);

		//Use of service
		todoService.updateTodo(todoDto);

		//Copy required attributes of response from DTO (DTO --> Response)
		BeanUtils.copyProperties(todoDto, restTodo);

		return restTodo;
	}

	//Retrieve user's todos
	@GetMapping("/users/{username}/todos")
	public List<RestTodo> getTodos(@PathVariable String username) {

		List<TodoDto> todos = new ArrayList<>();
		List<RestTodo> returnTodos = new ArrayList<>(); //Return object
		RestTodo restTodo = new RestTodo();

		//Use of service
		todos = todoService.retriveAllTodos(username);
		
		for (TodoDto todo : todos) {
			BeanUtils.copyProperties(todo, restTodo);

			returnTodos.add(restTodo);
			restTodo = new RestTodo();
		}
		return returnTodos;
	}

	//Retrieve a specific todo
	@GetMapping("/users/{username}/todos/{id}")
	public RestTodo getTodo(@PathVariable String username, @PathVariable long id) {

		RestTodo restTodo = new RestTodo(); //Return object

		//Use of service
		TodoDto todoDto = todoService.retriveTodo(username, id);

		//Copy required attributes of response from DTO (DTO --> Response)
		BeanUtils.copyProperties(todoDto, restTodo);

		return restTodo;
	}

	//Create a new todo
	@PostMapping("/users/{username}/todos/")
	public RestTodo createTodo(@PathVariable String username, @RequestBody TodoRequestBody todoDetails) {

		TodoDto todoDto = new TodoDto();

		//Setting some of DTO attributes
		todoDto.setUserName(username);

		BeanUtils.copyProperties(todoDetails, todoDto);

		//Use of service
		TodoDto serviceBack = todoService.createTodo(todoDto);

		if (serviceBack == null) {
			return null;
		}else {
			RestTodo returnObj = new RestTodo();

			BeanUtils.copyProperties(serviceBack, returnObj);

			return returnObj;
		}
	}

	//Delete a todo
	@DeleteMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id) {

		int sucessState = todoService.deleteTodo(id, username);

		if (sucessState == 1)
			return ResponseEntity.noContent().build();

		else
			return ResponseEntity.notFound().build();
	}
}

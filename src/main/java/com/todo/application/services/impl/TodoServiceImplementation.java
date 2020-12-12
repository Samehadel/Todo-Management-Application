package com.todo.application.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.application.data.entities.TodoEntity;
import com.todo.application.respository.TodoRepository;
import com.todo.application.services.TodoService;
import com.todo.application.shared.dto.TodoDto;

@Service
public class TodoServiceImplementation implements TodoService {

	@Autowired
	TodoRepository todoRepo;

	@Override
	public int deleteTodo(long id, String userName) {

		int successState = todoRepo.deleteByUserNameAndId(userName, id);

		return successState;
	}
	
	@Override
	public TodoDto updateTodo(TodoDto todoDto) {
		
		/*TodoEntity todo = todoRepo.updateByUsernameAndId(todoDto.getDescription(), todoDto.getDueDate(), todoDto.getUserName(), todoDto.getId());
		
		todoDto = new TodoDto();
		
		BeanUtils.copyProperties(todo, todoDto);*/
		
		TodoEntity entity = new TodoEntity();
		
		BeanUtils.copyProperties(todoDto, entity);
		
		System.out.println(entity.toString());
		entity.setTableId(getTableId(todoDto.getUserName(), todoDto.getId()));

		entity = todoRepo.save(entity);
				
		if(entity == null) return null;
				
		return todoDto;
	}

	@Override
	public List<TodoDto> retriveAllTodos(String userName) {

		List<TodoEntity> todos = todoRepo.findAllByUserName(userName);

		List<TodoDto> returnTodos = new ArrayList<>();

		TodoDto todoDto = new TodoDto();

		for (TodoEntity todo : todos) {
			BeanUtils.copyProperties(todo, todoDto);
			returnTodos.add(todoDto);
			todoDto = new TodoDto();
		}

		return returnTodos;
	}

	@Override
	public TodoDto retriveTodo(String userName, long id) {
		TodoEntity todo = todoRepo.findByUserNameAndId(userName, id);

		TodoDto todoDto = new TodoDto();

		BeanUtils.copyProperties(todo, todoDto);
	
		return todoDto;
	}

	@Override
	public TodoDto createTodo(TodoDto todoDto) {

		TodoEntity todoEntity = new TodoEntity();

		todoDto = assignId(todoDto);

		BeanUtils.copyProperties(todoDto, todoEntity);

		todoRepo.save(todoEntity);

		return todoDto;
	}

	public TodoDto assignId(TodoDto todoDto) {

		List<TodoEntity> todos = todoRepo.findAllByUserName(todoDto.getUserName());

		todoDto.setId(todos.size() + 1);

		return todoDto;
	}
	
	public long getTableId(String userName, long id) {
		TodoEntity entity = todoRepo.findByUserNameAndId(userName, id);
		
		
		return entity.getTableId();
	}

}

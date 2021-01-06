package com.todo.application.models.request;

import java.util.Date;

public class TodoRequestBody {

	private String description;
	private Date dueDate;
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	
}

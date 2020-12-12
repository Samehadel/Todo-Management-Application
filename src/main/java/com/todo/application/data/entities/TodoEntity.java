package com.todo.application.data.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "todos_tbl")
public class TodoEntity {
	
	@Id
	@GeneratedValue
	private long tableId;
	
	@Column(nullable = false)
	private long id;

	
	@Column(nullable = false, length = 75)
	private String userName;
	
	@Column(nullable = false, length = 200)
	private String description;
	
	@Column(nullable = false)
	private Date dueDate;
	
	@Column(nullable = false)
	private boolean isDone;

	//Must have default constructor
	public TodoEntity() {}
	
	public TodoEntity(String userName, String description, long id, Date dueDate, boolean isDone) {
		super();
		this.userName = userName;
		this.description = description;
		this.id = id;
		this.dueDate = dueDate;
		this.isDone = isDone;
	}

	
	public long getTableId() {
		return tableId;
	}

	public void setTableId(long tableId) {
		this.tableId = tableId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TodoEntity other = (TodoEntity) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TodoEntity [tableId=" + tableId + ", id=" + id + ", userName=" + userName + ", description="
				+ description + ", dueDate=" + dueDate + ", isDone=" + isDone + "]";
	}
	
	
	
}

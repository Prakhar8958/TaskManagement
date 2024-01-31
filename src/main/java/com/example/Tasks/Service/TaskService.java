package com.example.Tasks.Service;

import org.springframework.data.domain.Page;

import com.example.Tasks.Entity.Task;
import com.example.Tasks.Entity.User;

public interface TaskService {

	public Task saveTask(Task task);
	
	public Task getTaskById(int id);
	
	public Page<Task> getTaskByUser(User user,int pageNo);
	
	public Task updateTask(Task task);
	
	public boolean deleteTask(int id);
	
}

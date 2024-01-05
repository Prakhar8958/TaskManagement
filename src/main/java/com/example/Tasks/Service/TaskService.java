package com.example.Tasks.Service;

import java.util.List;

import com.example.Tasks.Entity.Task;
import com.example.Tasks.Entity.User;

public interface TaskService {

	public Task saveTask(Task task);
	
	public Task getTaskById(int id);
	
	public List<Task> getTaskByUser(User user);
	
	public Task updateTask(Task task);
	
	public boolean deleteTask(int id);
	
}

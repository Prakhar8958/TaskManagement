package com.example.Tasks.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Tasks.Entity.Task;
import com.example.Tasks.Entity.User;
import com.example.Tasks.Repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService{

	@Autowired
	private TaskRepository taskrepo;
	@Override
	public Task saveTask(Task task) {
		return taskrepo.save(task);
	}

	@Override
	public Task getTaskById(int id) {
		return taskrepo.findById(id).get();
	}

	@Override
	public List<Task> getTaskByUser(User user) {
		return taskrepo.findByUser(user);
	}

	@Override
	public Task updateTask(Task task) {
		return taskrepo.save(task);
	}

	@Override
	public boolean deleteTask(int id) {
    Task task=taskrepo.findById(id).get();
		if(task!=null) {
			taskrepo.delete(task);
			return true;
		}
		return false;
	}

}

package com.example.Tasks.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Tasks.Entity.Task;
import com.example.Tasks.Entity.User;

public interface TaskRepository extends JpaRepository<Task,Integer>{

	public List<Task> findByUser(User user);
	
}

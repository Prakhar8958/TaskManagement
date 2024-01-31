package com.example.Tasks.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Tasks.Entity.Task;
import com.example.Tasks.Entity.User;

public interface TaskRepository extends JpaRepository<Task,Integer>{

	public Page<Task> findByUser(User user,Pageable page);
	
}

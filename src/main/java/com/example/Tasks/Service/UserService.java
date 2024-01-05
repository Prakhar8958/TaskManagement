package com.example.Tasks.Service;

import com.example.Tasks.Entity.User;

public interface UserService {

	public User saveUser(User user);
	
	public boolean existEmailCheck(String email);
}

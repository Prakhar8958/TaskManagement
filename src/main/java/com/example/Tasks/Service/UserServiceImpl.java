package com.example.Tasks.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.Tasks.Entity.User;
import com.example.Tasks.Repository.UserRepository;

import jakarta.servlet.http.HttpSession;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Override
	public User saveUser(User user) {
		user.setRole("ROLE_USER");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User newUser=userrepo.save(user);
		return newUser;
	}

	@Override
	public boolean existEmailCheck(String email) {
		return userrepo.existsByEmail(email);
	}

	public void removeSessionMessage() {
		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
				.getSession();

		session.removeAttribute("msg");
	}
}

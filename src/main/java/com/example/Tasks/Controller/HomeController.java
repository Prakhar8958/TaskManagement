package com.example.Tasks.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Tasks.Entity.User;
import com.example.Tasks.Service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private UserService userservice;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	@GetMapping("/signin")
	public String login() {
		return "login";
	}
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user,HttpSession session) {
		
		boolean f=userservice.existEmailCheck(user.getEmail());
		if(f) {
			session.setAttribute("msg", "Email Already Exits");
		}
		else {
			User saveuser=userservice.saveUser(user);
			if(saveuser!=null) {
				session.setAttribute("msg", "User Saved Successfully");
			}
			else {
				session.setAttribute("msg", "Something went wrong");
			}
		}
		
		return "redirect:/register";
	}
	
}

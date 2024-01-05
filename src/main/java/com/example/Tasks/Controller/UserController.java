package com.example.Tasks.Controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Tasks.Entity.Task;
import com.example.Tasks.Entity.User;
import com.example.Tasks.Repository.TaskRepository;
import com.example.Tasks.Repository.UserRepository;
import com.example.Tasks.Service.TaskService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private TaskService taskservice;
	
	@Autowired
	private TaskRepository taskrepo;
	@ModelAttribute
	public User getUser(Principal p,Model m) {
		String email=p.getName();
		User user=userrepo.findByEmail(email);
		m.addAttribute("user", user);
		return user;
	}
	
	@GetMapping("/addTasks")
	public String addTasks() {
		return "add_tasks";
	}
	@GetMapping("/viewTasks")
	public String viewTasks(Model m,Principal p) {
		User user=getUser(p,m);
		List<Task> tasks=taskservice.getTaskByUser(user);
		Sort sort=Sort.by("date").descending();
		Pageable pageable=PageRequest.of(0, 2,sort);
		Page<Task> page=taskrepo.findAll(pageable);
		
		m.addAttribute("tasks", tasks);
		return "view_tasks";
	}
	@GetMapping("/editTasks/{id}")
	public String editTasks(@PathVariable int id,Model m) {
		Task task=taskservice.getTaskById(id);
		m.addAttribute("n", task);
		return "edit_tasks";
	}
	
	@PostMapping("/saveTask")
	public String saveTask(@ModelAttribute Task task,HttpSession session,Principal p,Model m) {
		task.setDate(LocalDate.now());
		task.setUser(getUser(p,m));
		
		Task saveTask=taskservice.saveTask(task);
		if(saveTask!=null) {
			session.setAttribute("msg", "Notes Saved Successfully");
		}
		else {
			session.setAttribute("msg", "Something went wrong");
		}
		return "redirect:/user/addTasks";
	}
	@PostMapping("/updateTask")
	public String updateTask(@ModelAttribute Task task,HttpSession session,Principal p,Model m) {
		task.setDate(LocalDate.now());
		task.setUser(getUser(p,m));
		
		Task saveTask=taskservice.saveTask(task);
		if(saveTask!=null) {
			session.setAttribute("msg", "Notes Update Successfully");
		}
		else {
			session.setAttribute("msg", "Something went wrong");
		}
		return "redirect:/user/viewTasks";
	}
	
	@GetMapping("/deleteTasks/{id}")
	public String deleteTasks(@PathVariable int id,HttpSession session) {
		boolean f=taskservice.deleteTask(id);
		if(f) {
			session.setAttribute("msg", "Task Delete Successfully");
		}
		else {
			session.setAttribute("msg", "Something went wrong");
		}	
		return "redirect:/user/viewTasks";   	
	}
	
}

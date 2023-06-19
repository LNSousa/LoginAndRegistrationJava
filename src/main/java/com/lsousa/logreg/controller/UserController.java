package com.lsousa.logreg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.lsousa.logreg.models.LoginUser;
import com.lsousa.logreg.models.User;
import com.lsousa.logreg.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "index.jsp";
	}
	
	@GetMapping("/welcome")
	public String welcome() {
		return "welcome.jsp";
	}
	
	@PostMapping("/register")
	public String create(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession session) {
		User registeredUser = service.register(newUser, result);
		
		if (result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		} else {			
			session.setAttribute("userId", registeredUser.getId());
			
			return "redirect:/welcome";
		}
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession session) {
		User loginUser = service.login(newLogin, result);
		
		if (result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "index.jsp";
		} else {			
			session.setAttribute("user", loginUser.getUsername());
			
			return "redirect:/welcome";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
}

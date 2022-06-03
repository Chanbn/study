package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.board.domain.user.User;
import com.board.mapper.UserMapper;
import com.board.service.UserService;

@Controller
@RequestMapping("/view")
public class LoginController {
	
	@Autowired
	UserService service;
	@GetMapping("/view/login") 
	public void gevtLogin() {
		
	}
	@GetMapping("/login")
	public void getLogin() {
		
	}
	
	@GetMapping("/signup")
	public void signup() {
		
	}
	
	@PostMapping("/signup")
	public String doSignup(Model model,@ModelAttribute("user") User user) {
		System.out.println("hjffjdhgdjhgk"+user.getEmail());
		service.Signup(user);
		return "redirect:/board/list?pageNum=1";
	}
}

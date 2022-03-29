package com.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class LoginController {

	@GetMapping("/login")
	public void getLogin() {
		
	}
	
	@GetMapping("/signup")
	public void signup() {
		
	}
}

package com.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatController {
	
	@GetMapping(value="/chat")
	public String chatGet() {
		System.out.println("ASDAS");
		return "/chat";
	} 
}

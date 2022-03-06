package com.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class BoardController {

	@GetMapping(value = "/board/write")
	public String tttestGG(Model model) {
		
		return "/board/write";
	}
}

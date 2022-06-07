package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.board.mapper.BoardMapper;

@Controller
@RequestMapping("/myPage/*")
public class myPageController {
	@Autowired
	private BoardMapper board;
	
	@GetMapping("/myPage")
	public void home() {
		
	}
	

}

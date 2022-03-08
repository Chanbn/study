package com.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.board.domain.BoardVO;
import com.board.service.BoardService;

@Controller
@RequestMapping("/")
public class BoardController {
	@Autowired
	BoardService boardService;
	
	@GetMapping(value = "/board/list")
	public String getList(Model model,BoardVO vo) {
		List<BoardVO> boardList =boardService.getList();
		model.addAttribute("boardList",boardList);
		return "/board/list";
	}
	
	@GetMapping(value = "/board/write")
	public String tttestGG(Model model) {
		
		return "/board/write";
	}
}

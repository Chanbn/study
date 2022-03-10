package com.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.board.domain.BoardVO;
import com.board.domain.Criteria;
import com.board.domain.PageDTO;
import com.board.service.BoardService;

@Controller
@RequestMapping("/")
public class BoardController {
	@Autowired
	BoardService boardService;
	
	@GetMapping(value = "/board/list")
	public void getList(Model model,BoardVO vo,Criteria cri) {
		List<BoardVO> boardList =boardService.getList(cri);
		int total = boardService.getTotal();
		model.addAttribute("boardList",boardList);
		model.addAttribute("pageMaker",new PageDTO(cri,total));
	}
	
	@GetMapping(value = "/board/write")
	public String tttestGG(Model model) {
		
		return "/board/write";
	}
}

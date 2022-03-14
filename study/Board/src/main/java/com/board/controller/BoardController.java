package com.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.BoardVO;
import com.board.domain.Criteria;
import com.board.domain.PageDTO;
import com.board.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	@Autowired
	BoardService boardService;
	
	@GetMapping(value = "/list")
	public void getList(Model model,Criteria cri,@RequestParam("pageNum") int pageNum) {
		List<BoardVO> boardList =boardService.getList(cri);
		int total = boardService.getTotal();
		model.addAttribute("boardList",boardList);
		model.addAttribute("pageMaker",new PageDTO(cri,total));
	}
	
	@GetMapping(value = "/get")
	public void get(Model model,@ModelAttribute("cri") Criteria cri, @RequestParam("idx") int idx,@RequestParam("pageNum") int pageNum) {
		cri.setPageNum(pageNum);
		model.addAttribute("board",boardService.get(idx));
	}
}

package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.board.domain.Criteria;
import com.board.domain.PageDTO;
import com.board.domain.user.User;
import com.board.mapper.BoardMapper;
import com.board.service.BoardService;

@Controller
@RequestMapping("/myPage/*")
public class myPageController {
	@Autowired
	BoardService boardService;
	
	@GetMapping("/myPage")
	public void home() {
		
	}
	
	@GetMapping("/myContentList")
	public void getList(Model model, @ModelAttribute("cri") Criteria cri) {

		model.addAttribute("boardList", boardService.getList(cri));
		System.out.println("keyword : " + cri.getKeyword() + " Email : " +  " Type : " + cri.getType());
		int total = boardService.getTotal(cri);
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	@GetMapping("/myCommentList")
	public void getCommentList(Model model, @ModelAttribute("cri") Criteria cri) {

		String writer = cri.getKeyword();
		System.out.println("writer ? :: "+writer+"type ??? "+cri.getType());
		model.addAttribute("commentList", boardService.getCommentList(cri));
		int total = boardService.getCommentCount(cri);
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
}

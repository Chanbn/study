package com.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
		System.out.println(cri.getPageNum());
		int total = boardService.getTotal();
		model.addAttribute("boardList",boardList);
		model.addAttribute("pageMaker",new PageDTO(cri,total));
	}
	
	@GetMapping(value = "/get")
	public void get(Model model,@ModelAttribute("cri") Criteria cri, @RequestParam("idx") int idx,@RequestParam("pageNum") int pageNum) {
		cri.setPageNum(pageNum);
		model.addAttribute("board",boardService.get(idx));
	}
	
	@GetMapping(value="/write")
	public void write(Model model,@ModelAttribute("vo") BoardVO vo) {
		
	}
	@PostMapping(value="/write")
	public String insertCon(Model model,@ModelAttribute("vo") BoardVO vo,HttpServletRequest request) {
		boardService.write(vo);
		return "redirect:/board/list?pageNum=1";
	}
	@GetMapping(value="/modify")
	public void modify1(Model model,@RequestParam("idx") int idx,@ModelAttribute("vo") BoardVO vo) {
		model.addAttribute("vo",boardService.get(vo.getIdx()));

	}
	@PostMapping(value="/modify")
	public String modify2(Model model, @ModelAttribute("vo") BoardVO vo) {
		System.out.println(vo.toString());
		boardService.modify(vo);
		return "redirect:/board/list?pageNum=1";
	}
	@GetMapping(value="/remove")
	public String remove(Model model,@RequestParam("idx") int idx) {
		boardService.remove(idx);
		return "redirect:/board/list?pageNum=1";
	}
}

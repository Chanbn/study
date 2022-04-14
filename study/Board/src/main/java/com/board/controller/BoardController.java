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
import org.thymeleaf.standard.expression.Each;

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
	public void getList(Model model,Criteria cri) {
		model.addAttribute("boardList",boardService.getList(cri));
		List<BoardVO> vo = boardService.getList(cri);
		vo.forEach(board->System.out.println(board));
		int total = boardService.getTotal(cri);
		model.addAttribute("pageMaker",new PageDTO(cri,total));
	}
	
	@GetMapping(value = "/get")
	public void get(Model model,@ModelAttribute("cri") Criteria cri, @RequestParam("idx") int idx,@RequestParam("pageNum") int pageNum) {
		cri.setPageNum(pageNum);
		model.addAttribute("board",boardService.get(idx));
	}
	
	@GetMapping(value="/write")
	public void write(Model model,@ModelAttribute("cri") Criteria cri,@ModelAttribute("vo") BoardVO vo) {
	}
	@PostMapping(value="/write")
	public String insertCon(Model model,@ModelAttribute("vo") BoardVO vo,HttpServletRequest request) {
		boardService.write(vo);
		return "redirect:/board/list?pageNum=1";
	}
	@GetMapping(value="/modify")
	public void modify1(Model model,@RequestParam("idx") Long idx,@ModelAttribute("cri") Criteria cri) {
		System.out.println(cri.getAmount());
		model.addAttribute("vo",boardService.get(idx));

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

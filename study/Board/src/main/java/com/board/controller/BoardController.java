package com.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.standard.expression.Each;

import com.board.domain.BoardVO;
import com.board.domain.Criteria;
import com.board.domain.PageDTO;
import com.board.domain.RatingVO;
import com.board.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	@Autowired
	BoardService boardService;

	@GetMapping(value = "/list")
	public void getList(Model model,Criteria cri) {
		model.addAttribute("boardList",boardService.getList(cri));
		int total = boardService.getTotal(cri);
		model.addAttribute("pageMaker",new PageDTO(cri,total));
	}
	
	@GetMapping(value = "/get")
	public void get(Model model,@ModelAttribute("cri") Criteria cri, @RequestParam("idx") int idx,@RequestParam("pageNum") int pageNum) {
		cri.setPageNum(pageNum);
		model.addAttribute("board",boardService.get(idx));
	}
	
	@ResponseBody
	@PostMapping(value="/get", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> getRating(Model model,@RequestBody RatingVO rvo) {
		System.out.println(rvo.getIdx());
		int chk = boardService.chooseRating(rvo.getIdx(), rvo.getWriter(), rvo.getChoose());
		if(chk==3) {
			return new ResponseEntity<>("이미 추천/비추천을 누른 게시글입니다.",HttpStatus.OK);
		}
		return chk==1? new ResponseEntity<>("success",HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="/write") 
	public void write(Model model,@ModelAttribute("cri") Criteria cri,@ModelAttribute("vo") BoardVO vo) {
	
	}
	
	@ResponseBody
	@PostMapping(value="/write", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> writeboard(@RequestBody BoardVO vo) {
		int chk = boardService.write(vo);
		System.out.println(chk);
		return chk == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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

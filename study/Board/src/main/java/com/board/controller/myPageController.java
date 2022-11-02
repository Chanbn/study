package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.board.domain.Criteria;
import com.board.domain.PageDTO;
import com.board.domain.UserRequestDTO;
import com.board.domain.user.User;

import com.board.service.BoardService;
import com.board.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/myPage/*")
public class myPageController {
	@Autowired
	BoardService boardService;
	@Autowired
	UserService userService;

	@Autowired
	AuthenticationManager authenticationManager;
	
	
	@GetMapping("/home")
	public void home() {
		
	}
	
	@GetMapping("/myContentList")
	public void getList(Model model, @ModelAttribute("cri") Criteria cri) {
		model.addAttribute("boardList", boardService.getList(cri));
		int total = boardService.getTotal(cri);
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	@GetMapping("/myCommentList")
	public void getCommentList(Model model, @ModelAttribute("cri") Criteria cri) {
		model.addAttribute("commentList", boardService.getCommentList(cri));
		int total = boardService.getCommentCount(cri);
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	@GetMapping("/myInfo")
	public void getInfo(Model model,@SessionAttribute("user") User user) {
		UserRequestDTO userInfo = userService.getUserinfo(user.getUsername());
		model.addAttribute("user", userInfo);
	}
	
	@PostMapping("/myInfo")
	public String modifyInfo(Model model,UserRequestDTO user) {
		String password = user.getPassword();
		int chk = userService.changeUserinfo(user);
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), password));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return "myPage/home";
	}
	
}

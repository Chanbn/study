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
	
	@GetMapping("/myInfo")
	public void getInfo(Model model,@SessionAttribute("user") User user) {
		UserRequestDTO userInfo = userService.getUserinfo(user.getUsername());
		model.addAttribute("user", userInfo);
		System.out.println("?SDA?F?ASD?SAD?ASD??????????????");
	}
	
	@PostMapping("/myInfo")
	public String modifyInfo(Model model,UserRequestDTO user) {
		String password = user.getPassword();
		int chk = userService.changeUserinfo(user);
		System.out.println("chk ?? chk ?? :: "+ chk);
		System.out.println("modifyinfo password?? "+user.getPassword());
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), password));
		System.out.println("GAJFSAD야이야앙");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		System.out.println("user info?? id?? "+user.getUsername()+ "password???? : "+user.getPassword());
		return "myPage/myPage";
	}
	
}

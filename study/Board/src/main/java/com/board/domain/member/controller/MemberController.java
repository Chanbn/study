package com.board.domain.member.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.domain.member.Member;
import com.board.domain.member.dto.MemberInfoDto;
import com.board.domain.member.dto.MemberSignUpDto;
import com.board.domain.member.exception.MemberException;
import com.board.domain.member.service.MemberService;
import com.board.domain.post.service.PostService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member/*")
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login() {
		
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public void signup() {
		
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST, consumes="application/json;")
	public ResponseEntity<String> signup2(@RequestBody MemberSignUpDto memberSignUpDto) throws Exception {
		try {
			memberService.signup(memberSignUpDto);
			return ResponseEntity.ok("redirect:/member/login");
		} catch (MemberException e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getExceptionType().getErrorMessage());
		}	
	} 
	
	@ResponseBody
	@RequestMapping(value = "/chk", method = RequestMethod.POST)
	public int NickNameCheck(String word, int type) throws Exception{ 
		System.out.println(word);
		System.out.println(type);
		if(word=="") {
			return -1;
		}
		int chk = memberService.existCheck(word, type);
		
		return chk;
	}
	
	@ResponseBody
	@RequestMapping(value = "/current", method = RequestMethod.GET)
	public ResponseEntity<MemberInfoDto> currentUser(@AuthenticationPrincipal UserDetails userDetails){
		MemberInfoDto member = memberService.currentMember(userDetails.getUsername());
		return ResponseEntity.ok(member);
	}
}

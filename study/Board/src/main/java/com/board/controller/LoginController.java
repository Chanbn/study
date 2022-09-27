package com.board.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.domain.UserRequestDTO;
import com.board.domain.user.User;
import com.board.mapper.UserMapper;
import com.board.service.UserService;
import com.board.validator.CustomValidators;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/view")
public class LoginController {
	
	@Autowired
	UserService service;
	
	private final CustomValidators.EmailValidator EmailValidator;
	private final CustomValidators.NameValidator NameValidator;
	
	@InitBinder
	public void validatorBinder(WebDataBinder binder) {
		binder.addValidators(NameValidator);
		binder.addValidators(EmailValidator);
	} 
	
	@GetMapping("/view/login") 
	public void gevtLogin() {
		
	}
	@GetMapping("/login")
	public void getLogin() {
		
	}
	
	@GetMapping("/signup")
	public void signup() {
		
	}
	
	@PostMapping("/signup")
	public String doSignup(@Valid UserRequestDTO user, Errors errors, Model model) {
		if(errors.hasErrors()) {
			System.out.println("에러 발생, 유효성 검사에서");
			model.addAttribute("user",user);
			
			Map<String,String> validatorResult =service.validatorHandling(errors); 
			for(String key : validatorResult.keySet()) {
				model.addAttribute(key, validatorResult.get(key));
				System.out.println(key+" : "+validatorResult.get(key));
			}
			return "/view/signup";
		}
		service.Signup(user);
		return "redirect:/board/list?pageNum=1";
	}
	
	@RequestMapping(value = "/chk", method = RequestMethod.POST)
	@ResponseBody
	public int NickNameCheck(String name) throws Exception{ 
		System.out.println(name);
		if(name=="") {
			return -1;
		}
		int chk = service.nameValid(name);
		return chk;
	}
}

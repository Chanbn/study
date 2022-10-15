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
import org.springframework.web.bind.annotation.RequestParam;
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
	public void getLogin(@RequestParam(value = "error",required = false) String error, @RequestParam(value = "exception", required = false) String exception, Model model) {
	model.addAttribute("error", error);
	model.addAttribute("exception", exception);
	}
	
	@GetMapping("/signup")
	public void signup() {
		
	}
	
	@PostMapping("/signup")
	public String doSignup(@Valid UserRequestDTO user, Errors errors, Model model) {
		System.out.println("post. signup 진입/////");
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
		System.out.println("에러없음. 다음단계로.../");
		service.Signup(user);
		return "redirect:/board/list?pageNum=1";
	}
	
	@RequestMapping(value = {"/chk","/myInfo"}, method = RequestMethod.POST)
	@ResponseBody
	public int NickNameCheck(String word, int type) throws Exception{ 
		System.out.println(word);
		System.out.println(type);
		if(word=="") {
			return -1;
		}
		int chk =0;
		if(type==1) {
			chk = service.idValid(word);			
		}else if(type==2) {
			chk = service.nickNameValid(word);
		}else if(type==3) {
			chk = service.emailValid(word);
		}
		return chk;
	}
}

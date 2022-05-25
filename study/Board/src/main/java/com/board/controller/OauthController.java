package com.board.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException; 
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.helper.constants.SocialLoginType;
import com.board.service.OauthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/login/oauth2/code")
@Slf4j
public class OauthController {
	
	private final OauthService oauthService;

	@GetMapping("/{socialLoginType}")
	public void socialLoginType(@PathVariable(name="socialLoginType") SocialLoginType socialLoginType){
		log.info(">>사용자로부터 SNS 로그인 요청을 받음 :: {} Social Login",socialLoginType);

		oauthService.request(socialLoginType);
		
	}
	
	@GetMapping("/{socialLoginType}/callback")
	public String callback(HttpSession httpSession,@PathVariable(name="socialLoginType") SocialLoginType socialLoginType,@RequestParam(name="code") String code) {
		log.info(">>사용자로부터 받은 CODE :: {}",code);
		String accessToken = oauthService.requestAccessToken(socialLoginType, code);
		System.out.println("accessToken :: "+accessToken);
		
				JSONParser jsonParser = new JSONParser();
		try {
			JSONObject jsonObject = (JSONObject) jsonParser.parse(accessToken);
//			System.out.println("jsonParser :: "+jsonObject.get("access_token"));
			String AccessToken =jsonObject.get("access_token").toString();
			httpSession.setAttribute("AccessToken", AccessToken);
			return "redirect:/board/list";

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "redirect:/board/list";
		}
		

	}
	
}

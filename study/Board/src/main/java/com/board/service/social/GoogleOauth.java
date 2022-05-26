package com.board.service.social;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GoogleOauth implements SocialOauth {
	
	@Value("${spring.security.oauth2.client.registration.google.client-id}")
	private String googleClientId;

	@Value("${spring.security.oauth2.client.registration.google.client-secret}")
	private String googleClientSecret;
	
	@Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
	private String googleCallbackUri;
	
	@Value("${myOauth.base.uri}")
	private String googleBaseUri;
	
	@Value("${myOauth.token.uri}")
	private String googleTokenUri;
	
	@Override
	public String getOauthRedirectURL() {
		// TODO Auto-generated method stub
		System.out.println(googleClientId);
		Map<String,Object> params = new HashMap<>();
		params.put("scope", "email");
		params.put("response_type", "code");
		params.put("client_id", googleClientId);
		params.put("redirect_uri", googleCallbackUri);
		params.put("access_type", "offline");
		String parameterString = params.entrySet().stream()
				.map(x->x.getKey()+"="+x.getValue())
				.collect(Collectors.joining("&"));
		System.out.println(googleBaseUri+"?"+parameterString);
		return googleBaseUri+"?"+parameterString;
	}

	@Override
	public String requestAccessToken(String code) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate =new RestTemplate();
		
		Map<String,Object> params = new HashMap<>();
		params.put("client_id", googleClientId);
		params.put("client_secret", googleClientSecret);
		params.put("code", code);
		params.put("redirect_uri", googleCallbackUri);
		params.put("grant_type", "authorization_code");
		ResponseEntity<String> resposeEntity = 
		restTemplate.postForEntity(googleTokenUri, params, String.class);
		
		if(resposeEntity.getStatusCode()==HttpStatus.OK) {
			System.out.println("SAD");
			return resposeEntity.getBody();
		}
		return "구글 로그인 요청 처리 실패";
	}

	@Override
	public String requestUserInfo(String AccessToken) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate();
		
		String userinfoUri = "https://www.googleapis.com/oauth2/v1/userinfo";
		Map<String,Object> params = new HashMap<>();
		params.put("access_token",AccessToken);
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(userinfoUri+"?access_token="+AccessToken, String.class);
		
		if(responseEntity.getStatusCode()==HttpStatus.OK) {
			return responseEntity.getBody();
		}
		return "정보획득 실패";
	}
	
	

}

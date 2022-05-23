package com.board.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.board.helper.constants.SocialLoginType;
import com.board.service.social.SocialOauth;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OauthService {

    private final HttpServletResponse response;
    private final List<SocialOauth> socialOauthList; 

    public void request(SocialLoginType socialLoginType) {

    	SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);
    	String redirectURL = socialOauth.getOauthRedirectURL();
    try {
    	response.sendRedirect(redirectURL);
    	
    }catch (IOException e) {
		// TODO: handle exception
        e.printStackTrace();
	}
    }
    
    public String requestAccessToken(SocialLoginType socialLoginType, String code) {
    	SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);
    	return socialOauth.requestAccessToken(code);
    }
    
    private SocialOauth findSocialOauthByType(SocialLoginType socialLoginType)
    {

    	return socialOauthList.stream()
    			.filter(x->x.type()==socialLoginType)
    			.findFirst()
    			.orElseThrow(()->new IllegalArgumentException("알 수 없는 SocialLoginType 입니다. "));
    }
}

package com.board.service.social;

import com.board.helper.constants.SocialLoginType;

public interface SocialOauth {
	String getOauthRedirectURL();
	String requestAccessToken(String code);
	String requestUserInfo(String AccessToken);
	
	default SocialLoginType type() {
		if(this instanceof GoogleOauth) {
			return SocialLoginType.GOOGLE;
		}else {
			return null;
		}
	}
}

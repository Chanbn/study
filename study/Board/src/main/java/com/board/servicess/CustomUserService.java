package com.board.service;

import java.util.Collection;
import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.board.configruation.auth.OAuthAttributes;
import com.board.domain.user.Ouser;
import com.board.domain.user.SessionOauth2User;
import com.board.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
	private final UserMapper userMapper;
	private final HttpSession httpSession;
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		// TODO Auto-generated method stub
		OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = delegate.loadUser(userRequest);
		String registrationId = userRequest.getClientRegistration().getClientId();
		String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().
				getUserInfoEndpoint().getUserNameAttributeName();
		OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName,oAuth2User.getAttributes());
		Ouser user = saveOrupdate(attributes);
		httpSession.setAttribute("user", new SessionOauth2User(user));
		
		return new DefaultOAuth2User(
				Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
						attributes.getAttributes(),
						attributes.getNameAttributeKey()
						);
	}
	
	private Ouser saveOrupdate(OAuthAttributes attributes) {
		Ouser user;
		if(userMapper.findByEmail(attributes.getEmail())!=null){
			user = userMapper.findByEmail(attributes.getEmail());
		}else {
			user = attributes.toEntity();
			userMapper.save(user);
			user = userMapper.findByEmail(attributes.getEmail());
		}
		return user;
	}

}

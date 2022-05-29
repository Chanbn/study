package com.board.domain.user;

import lombok.Getter;

@Getter
public class SessionOauth2User {
	private String name;
	private String email;
	private String picture;
	
	public SessionOauth2User(User oauth2User) {
		this.name = oauth2User.getName();
		this.email = oauth2User.getEmail();
	}
}

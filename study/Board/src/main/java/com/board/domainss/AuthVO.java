package com.board.domain;

import com.board.domain.user.Role;

import lombok.Data;

@Data
public class AuthVO {
	private String username;
	private String authority;
	
	public AuthVO(String username,String authority) {
		this.username = username;
		this.authority = authority;
	}
}

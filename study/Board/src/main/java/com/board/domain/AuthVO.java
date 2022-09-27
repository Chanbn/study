package com.board.domain;

import com.board.domain.user.Role;

import lombok.Data;

@Data
public class AuthVO {
	private String userid;
	private String authority;
	
	public AuthVO(String userid,String authority) {
		this.userid = userid;
		this.authority = authority;
	}
}

package com.board.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Ouser {
	private String name;
	private String email;
	private Role role;
	
	@Builder
	public Ouser(String name, String email, Role role) {
		this.name = name;
		this.email = email;
		this.role = role;
	}
	
	public Ouser update(String name) {
		this.name = name;
		return this;
	}
	
	public String getRoleKey() {
		return this.role.getKey();
	}
}

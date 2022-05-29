package com.board.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class User {
	private String username;
    private String name;
    private String password;
    private String email;
    private Role role;
	private boolean enabled;
	
    @Builder
    public User(String name, String email, Role role,String username,String password) {
    	this.name = name;
    	this.email= email;
    	this.role = role;
    	this.username=username;
    	this.password=password;
    }
    
    public User update(String name) {
        this.name = name;
        return this;
    }
    public String getRoleKey() {
        return this.role.getKey();
    }
}


package com.board.domain.user;

import java.util.List;

import com.board.domain.AuthVO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
	private String username;
    private String name;
    private String password;
    private String email;
	private List<AuthVO> authList;
	private boolean enabled;
	
    @Builder
    public User(String name, String email, List<AuthVO> authList, String username,String password) {

    	this.name = name;
    	this.email= email;
    	this.username=username;
    	this.password=password;
    	this.authList = authList;
    }
    
    @Builder
    public User(String name, String email, List<AuthVO> authList, String username,String password,boolean enabled) {
    	this.name=name;
    	this.email= email;
    	this.username=username;
    	this.password=password;
    	this.authList = authList;
    	this.enabled = enabled;    	
    }
    
 
    
    public User setPassword(String password) {
    	this.password=password;
    	return this;
    }
    
    public User update(String name) {
        this.name = name;
        return this;
    }
}


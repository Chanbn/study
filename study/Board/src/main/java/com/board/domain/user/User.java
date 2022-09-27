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
	private String userid;
    private String name;
    private String password;
    private String email;
	private List<AuthVO> authList;
	private boolean enabled;
	private String nickname;
	
    @Builder
    public User(String name, String email, List<AuthVO> authList, String userid,String password,String nickname) {

    	this.name = name;
    	this.email= email;
    	this.userid=userid;
    	this.password=password;
    	this.authList = authList;
    	this.nickname = nickname;
    }
    
    @Builder
    public User(String name, String email, List<AuthVO> authList, String userid,String password,boolean enabled,String nickname) {
    	this.name=name;
    	this.email= email;
    	this.userid=userid;
    	this.password=password;
    	this.authList = authList;
    	this.enabled = enabled;    	
    	this.nickname = nickname;
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


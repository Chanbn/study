package com.board.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import com.board.domain.AuthVO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {
	private String username;
    private String name;
    private String password;
    private String email;
	private List<AuthVO> authList;
	private boolean enabled;
	private String nickname;
	private LocalDateTime createdate;
	private LocalDateTime modifieddate;
	
    @Builder
    public User(String name, String email, List<AuthVO> authList, String username,String password,String nickname) {

    	this.name = name;
    	this.email= email;
    	this.username=username;
    	this.password=password;
    	this.authList = authList;
    	this.nickname = nickname;
    }
    
    @Builder
    public User(String name, String email, List<AuthVO> authList, String username,String password,boolean enabled,String nickname) {
    	this.name=name;
    	this.email= email;
    	this.username=username;
    	this.password=password;
    	this.authList = authList;
    	this.enabled = enabled;    	
    	this.nickname = nickname;
    }
    
    @Builder
    public User(String name, String email, List<AuthVO> authList, String username,String password,boolean enabled,String nickname,LocalDateTime createdate,LocalDateTime modifieddate) {
    	this.name=name;
    	this.email= email;
    	this.username=username;
    	this.password=password;
    	this.authList = authList;
    	this.enabled = enabled;    	
    	this.nickname = nickname;
    	this.createdate=createdate;
    	this.modifieddate=modifieddate;
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


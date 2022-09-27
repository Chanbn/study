package com.board.security.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.board.domain.user.User;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PrincipalDetails implements UserDetails,OAuth2User{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User user;
	private Map<String, Object> attributes;

	public PrincipalDetails(User user) {
		System.out.println("ASFASD"+user.getEmail());
		this.user = user;
	}
	
	public PrincipalDetails(User user,Map<String,Object> attributes) {
		System.out.println("2   ::"+this.getClass().getMethods().toString());
		this.user = user;
		this.attributes=attributes;
	}
	
	public User getUser() {
		return user;
	}

	
	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return attributes;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return user.getName().toString();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
System.out.println(user.getAuthList());
		return user.getAuthList().stream()
				.map(auth -> new SimpleGrantedAuthority(auth.getAuthority()))
				.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword().toString();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUserid();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}

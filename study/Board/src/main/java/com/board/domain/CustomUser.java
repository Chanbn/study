//package com.board.domain;
//
//import java.util.Collection;
//import java.util.stream.Collectors;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import lombok.Getter;
//
//@Getter
//public class CustomUser extends User{
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	private UserVO uvo;
//	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
//		super(username, password, authorities);
//		// TODO Auto-generated constructor stub
//	}
//public CustomUser(UserVO vo) {
//	// TODO Auto-generated constructor stub
//	super(vo.getUsername(),vo.getPassword(),vo.getAuthList().stream().map(auth->new SimpleGrantedAuthority(auth.getAuthority())).collect(Collectors.toList()));
//	this.uvo = vo;
//	System.out.println(uvo.toString());
//}
//}

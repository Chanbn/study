package com.board.security.config;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.board.domain.user.User;
import com.board.mapper.UserMapper;

import lombok.Setter;

@Service

public class LoginIdPwValidator implements UserDetailsService{
	   @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	@Setter(onMethod_ = {@Autowired})
	private UserMapper mapper;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("S2faaaffAD");
		User user;
		user = mapper.read(username);
		System.out.println(user.getPassword());
		return user==null?null:new PrincipalDetails(user);
	}

	
}

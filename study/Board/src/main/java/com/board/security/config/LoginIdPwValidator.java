package com.board.security.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.board.domain.CustomUser;
import com.board.domain.UserVO;
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
		UserVO vo;
		vo = mapper.read(username);
		System.out.println(new CustomUser(vo).toString());
		return vo==null? null:new CustomUser(vo);
	}

	
}

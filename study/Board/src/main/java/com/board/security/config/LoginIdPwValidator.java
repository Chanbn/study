package com.board.security.config;

import java.util.List;
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

import com.board.domain.AuthVO;
import com.board.domain.user.User;
import com.board.mapper.UserMapper;

import lombok.Setter;

@Service

public class LoginIdPwValidator implements UserDetailsService{
	
	  

	@Setter(onMethod_ = {@Autowired})
	private UserMapper mapper;
	@Autowired
	HttpSession session;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println(username);
		User user;
		

		user = mapper.read(username);
		System.out.println("여기까지는?");
		if(user==null) {
			System.out.println("user is null");
			return null;
		}else {
			System.out.println("user is not null"+user.getPassword());
		}
		System.out.println(user.getAuthList());
		List<AuthVO> authVO = user.getAuthList();
		
		User us=new User(user.getName(), user.getEmail(), authVO, user.getUsername(), user.getPassword(),user.getNickname());
		session.setAttribute("user", us);
		System.out.println("LoginPwValidator :: "+us.toString());
		return user==null?null:new PrincipalDetails(us);
	}

	
}

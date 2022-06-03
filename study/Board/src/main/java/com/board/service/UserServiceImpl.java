package com.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.board.domain.user.User;
import com.board.mapper.UserMapper;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper mapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public User read(String username) {
		// TODO Auto-generated method stub
		return mapper.read(username);
	}
	@Override
	public int Signup(User user) {
		// TODO Auto-generated method stub
		System.out.println(user.getName()+ user.getEmail()+user.getPassword());
		String encodePassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodePassword);
		System.out.println("vo에 들갔나? "+user.getPassword());
		int chk = mapper.Signup(user);
		if(chk==0) {
			System.out.println("야이!");
			return 0;
		}
		return mapper.SignupAuth(user.getUsername());
	}


}

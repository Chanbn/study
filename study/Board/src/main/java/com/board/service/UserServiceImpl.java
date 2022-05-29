package com.board.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import com.board.domain.user.User;
import com.board.domain.user.UserDTO;
import com.board.mapper.UserMapper;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper mapper;
	@Override
	public User read(String username) {
		// TODO Auto-generated method stub
		return mapper.read(username);
	}
	@SuppressWarnings("static-access")
	@Override
	public int Signup(UserDTO vo) {
		// TODO Auto-generated method stub
		System.out.println(vo.getName()+ vo.getEmail()+vo.getPassword());
		int chk = mapper.Signup(vo);
		if(chk==0) {
			System.out.println("야이!");
			return 0;
		}
		return mapper.SignupAuth(vo.getEmail());
	}


}

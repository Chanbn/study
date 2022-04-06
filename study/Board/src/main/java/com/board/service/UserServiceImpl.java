package com.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.board.domain.AuthVO;
import com.board.domain.UserVO;
import com.board.mapper.UserMapper;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper mapper;
	@Override
	public UserVO read(String username) {
		// TODO Auto-generated method stub
		return mapper.read(username);
	}
	@Override
	public int Signup(UserVO vo) {
		// TODO Auto-generated method stub
		vo.setPassword(new BCryptPasswordEncoder().encode(vo.getPassword()));
		int chk = mapper.Signup(vo);
		if(chk==0) {
			return 0;
		}
		return mapper.SignupAuth(vo.getUsername());
	}


}

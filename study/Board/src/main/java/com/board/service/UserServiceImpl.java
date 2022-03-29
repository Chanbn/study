package com.board.service;

import org.springframework.stereotype.Service;

import com.board.domain.AuthVO;
import com.board.domain.UserVO;
import com.board.mapper.UserMapper;
@Service
public class UserServiceImpl implements UserService {
	UserMapper mapper;
	@Override
	public UserVO read(String username) {
		// TODO Auto-generated method stub
		return mapper.read(username);
	}
	@Override
	public int Signup(UserVO vo) {
		// TODO Auto-generated method stub
		int chk = mapper.Signup(vo);
		if(chk==0) {
			return 0;
		}
		return mapper.SignupAuth(vo.getUsername());
	}


}

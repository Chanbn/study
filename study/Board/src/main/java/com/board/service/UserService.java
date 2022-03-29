package com.board.service;

import com.board.domain.AuthVO;
import com.board.domain.UserVO;

public interface UserService {
	public UserVO read(String username);
	public int Signup(UserVO vo);
}

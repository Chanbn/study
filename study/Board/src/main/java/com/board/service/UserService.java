package com.board.service;

import com.board.domain.AuthVO;
import com.board.domain.UserVO;
import com.board.domain.user.User;
import com.board.domain.user.UserDTO;

public interface UserService {
	public User read(String username);
	public int Signup(UserDTO vo);
}

package com.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.UserVO;
import com.board.domain.user.Ouser;
import com.board.domain.user.User;
import com.board.domain.user.UserDTO;

@Mapper
public interface UserMapper {
	public User read(String username);
	public int Signup(UserDTO vo);
	public int SignupAuth(String username);
	public User findByEmail(String email);
	public void save(User ouser);
}

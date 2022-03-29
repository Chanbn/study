package com.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.UserVO;

@Mapper
public interface UserMapper {
	public UserVO read(String username);
	public int Signup(UserVO vo);
	public int SignupAuth(String username);
}

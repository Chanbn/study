package com.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.UserVO;
import com.board.domain.user.Ouser;

@Mapper
public interface UserMapper {
	public UserVO read(String username);
	public int Signup(UserVO vo);
	public int SignupAuth(String username);
	public Ouser findByEmail(String email);
	public void save(Ouser ouser);
}

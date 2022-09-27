package com.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.BoardVO;
import com.board.domain.UserRequestDTO;
import com.board.domain.user.User;
@Mapper
public interface UserMapper {
	public User read(String userid);
	public int Signup(UserRequestDTO user);
	public int SignupAuth(String userid);
	public User findByEmail(String email);
	public void save(User ouser);
	public BoardVO findMyPost(String email);
	public int emailValid(String email);
	public int nickNameValid(String name);
}

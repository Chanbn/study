package com.board.service;


import java.util.Map;

import org.springframework.validation.Errors;

import com.board.domain.UserRequestDTO;
import com.board.domain.user.User;

public interface UserService {
	public User read(String username);
	public Map<String,String> validatorHandling(Errors error);
	public int Signup(UserRequestDTO user);
	public int emailValid(String email);
	public int nickNameValid(String nickname);
	public int idValid(String username);
	public UserRequestDTO getUserinfo(String username);
	public int changeUserinfo(UserRequestDTO user);
	
} 

package com.board.service;


import java.util.Map;

import org.springframework.validation.Errors;

import com.board.domain.UserRequestDTO;
import com.board.domain.user.User;

public interface UserService {
	public User read(String userid);
	public Map<String,String> validatorHandling(Errors error);
	public int Signup(UserRequestDTO user);
	public int emailValid(String email);
	public int nameValid(String name);
}

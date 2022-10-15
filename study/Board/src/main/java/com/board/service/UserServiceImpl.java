package com.board.service;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.board.domain.UserRequestDTO;
import com.board.domain.user.User;
import com.board.mapper.UserMapper;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper mapper;

	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public User read(String username) {
		// TODO Auto-generated method stub
		return mapper.read(username);
	}
	@Override
	public int Signup(UserRequestDTO user) {
		// TODO Auto-generated method stub
		System.out.println(user.getName()+ user.getEmail()+user.getPassword());
		String encodePassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodePassword);
		int chk = mapper.Signup(user);
		if(chk==0) {
			return 0;
		}
		return mapper.SignupAuth(user.getUsername());
	}
	@Override
	public Map<String, String> validatorHandling(Errors errors) {
		// TODO Auto-generated method stub
		Map<String,String> validatorResult = new HashMap<>();
		
		for(FieldError error : errors.getFieldErrors()) {
			String validKeyName = String.format("valid_%s", error.getField());
			validatorResult.put(validKeyName, error.getDefaultMessage());
		}
		
		return validatorResult;
	}
	@Override
	public int emailValid(String email) {
		// TODO Auto-generated method stub
		int resultValue = mapper.emailValid(email);

		return resultValue;
	}
	@Override
	public int nickNameValid(String nickname) {
		// TODO Auto-generated method stub
		int resultValue = mapper.nickNameValid(nickname);
			
		return resultValue;
	}
	@Override
	public int idValid(String username) {
		// TODO Auto-generated method stub
		int resultValue = mapper.idValid(username);
		return resultValue;
	}
	
	@Transactional
	@Override
	public int changeUserinfo(UserRequestDTO user) {
		// TODO Auto-generated method stub
		String encodePassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodePassword);
		int resultValue = mapper.changeUserinfo(user);


		
		return resultValue;
	}
	@Override
	public UserRequestDTO getUserinfo(String username) {
		// TODO Auto-generated method stub
		return mapper.getUserinfo(username);
	}



}

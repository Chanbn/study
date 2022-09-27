package com.board.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.board.domain.UserRequestDTO;
import com.board.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CustomValidators {

	
	@RequiredArgsConstructor
	@Component
	public static class EmailValidator extends Abstractvalidator<UserRequestDTO>{
		@Autowired
		UserService service;

		@Override
		public void doValidate(UserRequestDTO dto, Errors errors) {
			// TODO Auto-generated method stub
			if(service.emailValid(dto.getEmail())>0) {
				errors.rejectValue("email", "이메일 중복 오류", "이미 등록되어있는 이메일 입니다.");
			}
		}
		
	}
	
	@RequiredArgsConstructor
	@Component
	public static class NameValidator extends Abstractvalidator<UserRequestDTO>{
		@Autowired
		UserService service;

		@Override
		public void doValidate(UserRequestDTO dto, Errors errors) {
			// TODO Auto-generated method stub
			if(service.nameValid(dto.getNickname())>0) {
				errors.rejectValue("nickname", "닉네임 중복 오류", "이미 등록되어있는 닉네임 입니다.");			}
		}
	}
}

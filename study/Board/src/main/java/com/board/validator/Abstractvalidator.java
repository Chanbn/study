package com.board.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class Abstractvalidator<T> implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		try {
			doValidate((T) target,errors);
		} catch (RuntimeException e) {
			// TODO: handle exception
			log.info("중복오류 발생",e);
		}
		
	}
	
	public abstract void doValidate(final T dto, Errors errors);

}

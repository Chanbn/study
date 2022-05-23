package com.board.helper.converter;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import com.board.helper.constants.SocialLoginType;


@Configuration
public class SocialLoginTypeConverter implements Converter<String,SocialLoginType> {

	@Override
	public SocialLoginType convert(String s) {
		// TODO Auto-generated method stub
		return SocialLoginType.valueOf(s.toUpperCase());
	}

}

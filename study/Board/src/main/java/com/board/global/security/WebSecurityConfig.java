package com.board.global.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		web
		.ignoring()
		.antMatchers("/resources/**");
	}
	 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests()
		.antMatchers("/member/**","/board/list").permitAll()
		.antMatchers("/myPage/myinfo","/myPage/myContentList","/myPage/myCommentList","/myPage/home").authenticated()
		.and()
		.formLogin()
		.loginPage("/member/login")
		.loginProcessingUrl("/member/loginProc") // 추가
		.usernameParameter("username")
		.passwordParameter("pw")
		.defaultSuccessUrl("/board/list")
		.failureForwardUrl("/board/list") 
		.and()
		.logout()
		.logoutUrl("/logout")
		.logoutSuccessUrl("/board/list")
		.and().csrf();
	}
	
	@Bean
	public BCryptPasswordEncoder encodePassword() {
		return new BCryptPasswordEncoder();
	}
	
}

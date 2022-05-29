package com.board.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


import com.board.service.PrincipalOauth2UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	

	private final LoginIdPwValidator loginidPwValidator;
	private final PrincipalOauth2UserService principalOauth2UserService;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http 
		.authorizeRequests()
		.antMatchers("/board/list").permitAll()
		.antMatchers("/view/signup").permitAll()
		.and()
		.formLogin()
		.loginPage("/view/login")
		.loginProcessingUrl("/loginProc")
		.usernameParameter("username")
		.passwordParameter("pw")
		.defaultSuccessUrl("/board/list",true)
		.permitAll()
		.and()
		.logout()
		.logoutUrl("/view/logoutProc")
		.logoutSuccessUrl("/board/list")
		.and()
		.oauth2Login()
		.defaultSuccessUrl("/board/list")
		.failureUrl("/view/SocialLogin")	
		.userInfoEndpoint()
		.userService(principalOauth2UserService)
		;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		 web.ignoring().antMatchers("/static/js/**","/static/css/**","/static/img/**","/static/frontend/**");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(loginidPwValidator);
	}
}

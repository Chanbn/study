package com.board.global.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.board.domain.member.repository.MemberRepository;
import com.board.global.jwt.JwtAuthenticationFilter;
import com.board.global.jwt.JwtAuthorizationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final MemberRepository memberRepository;
	private final CorsConfig corsConfig;
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

return		http
		.httpBasic().disable()
		.csrf().disable().sessionManagement()
		.and()
		.formLogin().disable()
		.apply(new MyCustom())
		.and()
		.authorizeRequests(authroize -> authroize.antMatchers("/myPage/**")
				.access("hasRole('ROLE_USER')")
				.anyRequest().permitAll()).build();
				 
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
//				.antMatchers("/member/**", "/board/list").permitAll().antMatchers("/myPage/myinfo",
//						"/myPage/myContentList", "/myPage/myCommentList", "/myPage/home", "/board/write")
//				.hasRole("USER")
//				.anyRequest().permitAll()
				
//		.and()
//		.addFilter(new JwtAuthenticationFilter(authenticationManager))
//		.addFilter(new JwtAuthorizationFilter(authenticationManager, memberRepository))

	}

	@Bean
	public BCryptPasswordEncoder encodePassword() {
		return new BCryptPasswordEncoder();
	}

	
	  public class MyCustom extends AbstractHttpConfigurer<MyCustom, HttpSecurity>{
	  
	  @Override public void configure(HttpSecurity http) throws Exception { 
		  // TODO	  Auto-generated method stub
	  
	  AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
	  
	  http
	  .addFilter(corsConfig.corsFilter())
	  .addFilter(new JwtAuthenticationFilter(authenticationManager)) 
	  .addFilter(new JwtAuthorizationFilter(authenticationManager,memberRepository)); } }
	   
}

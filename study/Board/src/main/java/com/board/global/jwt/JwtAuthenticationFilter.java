package com.board.global.jwt;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.board.domain.member.dto.MemberLoginRequestDto;
import com.board.global.Login.MemberDetails;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

//클라이언트 요청 시 JWT 인증을 하기 위해 설치하는 커스텀필터
//UsernamePasswordAuthenticationFilter 이전에 실행
//이 필터를 통과하면 이후에 Username + Password를 통한 인증을 이 필터를 통하여 수행 (JWT 인증으로 대체)
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		setFilterProcessesUrl("/member/login");
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// TODO Auto-generated method stub
			log.info("로그인 시도 : JwtAuthenticationFilter.attemptAuthentication");
			ObjectMapper objectMapper = new ObjectMapper();
			MemberLoginRequestDto memberLoginRequestDto = null;
			try{
				log.info("-0--------0---------0-");
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
				
//				log.info(authenticationToken.getPrincipal().toString());
//				log.info(authenticationToken.getCredentials().toString());
				log.info("===============================================");

				Authentication authentication = authenticationManager.authenticate(authenticationToken);
				MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
				log.info("Authentication : '"+memberDetails.getMember().getUsername());
				
				return authentication;	
			}catch (AuthenticationException e) {
		        throw e;
		    } catch (Exception e) {
		        throw new RuntimeException("Failed to authenticate user", e);
		    }
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		MemberDetails memberDetails = (MemberDetails) authResult.getPrincipal();
		
		String jwtToken = JWT.create()
							.withSubject(memberDetails.getUsername())
							.withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))
							.withClaim("id",memberDetails.getMember().getId())
							.withClaim("username",memberDetails.getMember().getUsername())
							.sign(Algorithm.HMAC512(JwtProperties.SECRET));
		
		String refreshToken = JWT.create()
								.withSubject(memberDetails.getUsername())
								.withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))
								.withClaim("id", memberDetails.getMember().getId())
								.withClaim("username", memberDetails.getMember().getUsername())
								.sign(Algorithm.HMAC512(JwtProperties.SECRET));
		
		Cookie cookie = new Cookie("refreshToken",refreshToken);
		cookie.setMaxAge(JwtProperties.EXPIRATION_TIME);
		cookie.setHttpOnly(true);
//		cookie.setSecure(true);
		cookie.setPath("/");
		response.addCookie(cookie);
		
		response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX+jwtToken);
		
		response.sendRedirect("/board/list");
	}

}

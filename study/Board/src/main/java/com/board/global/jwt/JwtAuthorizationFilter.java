package com.board.global.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.board.domain.member.Member;
import com.board.domain.member.repository.MemberRepository;
import com.board.global.Login.MemberDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
	private final MemberRepository memberRepository;
	

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager,MemberRepository memberRepository) {
		super(authenticationManager);
		// TODO Auto-generated constructor stub
		this.memberRepository = memberRepository;
		
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		String header = request.getHeader(JwtProperties.HEADER_STRING);
		if(header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		
		log.info("header :'"+header+"'");
		try {
			String token = request.getHeader(JwtProperties.HEADER_STRING)
					.replace(JwtProperties.TOKEN_PREFIX,"");
			
			String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(token)
					.getClaim("username").asString();
			
			if(username!=null) {
				Member member = memberRepository.findByUsername(username).orElseThrow();
				
				MemberDetails memberDetails = new MemberDetails(member);
				Authentication authentication = new UsernamePasswordAuthenticationToken(memberDetails, null,memberDetails.getAuthorities());
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		    log.error("Error during authentication", e);
		}

		chain.doFilter(request, response);
	}

	



}

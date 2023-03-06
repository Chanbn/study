package com.board.global.Login;




import javax.servlet.http.HttpSession;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.board.domain.member.Member;
import com.board.domain.member.dto.MemberSessionDto;
import com.board.domain.member.exception.MemberException;
import com.board.domain.member.exception.MemberExceptionType;
import com.board.domain.member.repository.MemberRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService{

	private final MemberRepository memberRepository;
	private final HttpSession session;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("loadUserByUsername :: ");
		Member member = memberRepository.findByUsername(username).orElseThrow(()-> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));
		if(member == null) {
		}
		MemberSessionDto user= MemberSessionDto.builder().email(member.getEmail()).id(member.getId()).name(member.getName()).nickname(member.getNickname()).role(member.getRole()).username(member.getUsername()).build();
		
		session.setAttribute("user", user);
		
		System.out.println("loadUserByUsername :: "+user.toString());

		return new MemberDetails(member);		
	}

}

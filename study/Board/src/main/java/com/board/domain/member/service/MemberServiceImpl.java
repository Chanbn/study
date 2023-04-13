package com.board.domain.member.service;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.board.domain.member.Member;
import com.board.domain.member.dto.MemberInfoDto;
import com.board.domain.member.dto.MemberSignUpDto;
import com.board.domain.member.exception.MemberException;
import com.board.domain.member.exception.MemberExceptionType;
import com.board.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;

	@Override
	public void signup(MemberSignUpDto memberSignUpDto) {
		// TODO Auto-generated method stub
		Member member = memberSignUpDto.toEntity();
		log.info("here");
		member.addRoles_USER();
		log.info("why");
		member.encodePassword(passwordEncoder);

		if (memberRepository.existsByUsername(member.getUsername())) {
			throw new MemberException(MemberExceptionType.ALREADY_EXIST_USERNAME);
		}
		memberRepository.save(member);
	} 

	@Override
	public int existCheck(String word,int type) {
		// TODO Auto-generated method stub
		int check = 0;
		switch (type) {
		case 1:
			check = memberRepository.existsByUsername(word)==true?1:0;			
			break;
		case 2:
			check = memberRepository.existsByNickname(word)==true?1:0;
			break;
		case 3:
			check = memberRepository.existsByEmail(word)==true?1:0;
			break;
		default:
			break;
		}
		return check;
	}

	@Override
	public MemberInfoDto currentMember(String username) {
		// TODO Auto-generated method stub
		MemberInfoDto member = new MemberInfoDto(memberRepository.findByUsername(username).orElseThrow(()-> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER)));
		return member;
	}



}

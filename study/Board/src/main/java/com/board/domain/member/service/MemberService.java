package com.board.domain.member.service;

import com.board.domain.member.dto.MemberInfoDto;
import com.board.domain.member.dto.MemberSignUpDto;

public interface MemberService {
	void signup(MemberSignUpDto memberSignUpDto);
	int existCheck(String word,int type);
	MemberInfoDto currentMember(String username);
	
}

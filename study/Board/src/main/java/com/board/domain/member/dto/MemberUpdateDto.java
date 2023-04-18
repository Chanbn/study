package com.board.domain.member.dto;

import com.board.domain.member.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberUpdateDto {

	private String username;
	private String nickname;
	private String password;
	
	
	public Member toEntity() {
		return Member.builder()
				.nickname(nickname)
				.password(password)
				.build();
	}
}

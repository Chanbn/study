package com.board.domain.member.dto;

import com.board.domain.member.Member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberProfileDto {
	private String username;
	private String name;
	private String nickname;
	private String email;
	private Long id;
	
	@Builder
	public MemberProfileDto(Member member) {
		this.username = member.getUsername();
		this.nickname = member.getNickname();
		this.email = member.getEmail();
		this.id = member.getId();
		this.name = member.getName();
	}
	
	@Builder
	public void setDto(MemberSessionDto member) {
		this.username = member.getUsername();
		this.nickname = member.getNickname();
		this.email = member.getEmail();
		this.id = member.getId();
		this.name = member.getName();
	}
	
	public Member toEntity() {
		return Member.builder()
				.id(id)
				.username(username)
				.nickname(nickname)
				.email(email)
				.name(name)
				.build();
	}
}

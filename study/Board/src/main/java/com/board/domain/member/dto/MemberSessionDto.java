package com.board.domain.member.dto;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.board.domain.member.Role;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberSessionDto {

	private Long id;
	private String username;
	private String name;
	private String email;
	private String nickname;
	private Role role;
	
	@Builder
	public MemberSessionDto(Long id, String username, String name, String email,String nickname,Role role)
	{
		this.id = id;
		this.username = username;
		this.name = name;
		this.email = email;
		this.nickname = nickname;
		this.role = role;
	}
}

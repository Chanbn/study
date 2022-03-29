package com.board.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserVO {

	private String username;
	private String password;
	private List<AuthVO> authList;
	private int enabled;
}

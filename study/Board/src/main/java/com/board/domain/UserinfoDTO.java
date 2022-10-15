package com.board.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserinfoDTO {
	String username;
	String password;
	String nickname;
	String name;
	String email;
}

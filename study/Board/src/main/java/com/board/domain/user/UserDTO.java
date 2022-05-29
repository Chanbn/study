package com.board.domain.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class UserDTO {
	private String username;
    private String name;
    private String password;
    private String email;
    private Role role;
	private boolean enabled;
}

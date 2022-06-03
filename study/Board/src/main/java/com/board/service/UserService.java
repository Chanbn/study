package com.board.service;


import com.board.domain.user.User;

public interface UserService {
	public User read(String username);
	public int Signup(User user);
}

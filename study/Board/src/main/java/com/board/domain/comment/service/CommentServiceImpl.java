package com.board.domain.comment.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.board.domain.comment.Comment;
import com.board.domain.comment.repository.CommentRepository;
import com.board.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

	private final CommentRepository commentRepository;
	
	@Override
	public void save(Comment comment) {
		// TODO Auto-generated method stub
		commentRepository.save(comment);
	}

}

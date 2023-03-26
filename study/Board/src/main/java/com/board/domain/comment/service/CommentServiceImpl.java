package com.board.domain.comment.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.board.domain.comment.Comment;
import com.board.domain.comment.dto.CommentSaveDto;
import com.board.domain.comment.repository.CommentRepository;
import com.board.domain.member.Member;
import com.board.domain.member.repository.MemberRepository;
import com.board.domain.post.Post;
import com.board.domain.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

	private final CommentRepository commentRepository;
	private final MemberRepository memberRepository;
	private final PostRepository postRepository;
	
	@Override
	public void save(CommentSaveDto commentData) {
		// TODO Auto-generated method stub
		Member member = memberRepository.findByUsername(commentData.getUsername()).orElseThrow();
		Post post =postRepository.findByIdx(commentData.getPostId()).orElseThrow();
		Comment comment = commentData.toEntity(member, post);
		
		commentRepository.save(comment);
	}

}

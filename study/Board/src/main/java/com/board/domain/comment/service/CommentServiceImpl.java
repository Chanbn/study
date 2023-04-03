package com.board.domain.comment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.board.domain.comment.Comment;
import com.board.domain.comment.dto.CommentGetDto;
import com.board.domain.comment.dto.CommentSaveDto;
import com.board.domain.comment.repository.CommentRepository;
import com.board.domain.member.Member;
import com.board.domain.member.repository.MemberRepository;
import com.board.domain.post.Post;
import com.board.domain.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

	private final CommentRepository commentRepository;
	private final MemberRepository memberRepository;
	private final PostRepository postRepository;
	
	@Override
	public void save(CommentSaveDto commentData) {
		// TODO Auto-generated method stub

		Member member = memberRepository.findById(commentData.getUserId()).orElseThrow();
		Post post =postRepository.findByIdx(commentData.getPostId()).orElseThrow();
		Comment comment = commentData.toEntity(member, post);
		log.info("에러" + commentData.getParentId());
		if(commentData.getParentId()!=null) {
			Comment parentComment = commentRepository.findById(commentData.getParentId()).orElseThrow();
			comment.setParentComment(parentComment);	
			parentComment.addChildComment(comment);
		}else if(commentData.getParentId()==null) {
			log.info("comment.getIdx() :'"+comment.getIdx()+"'");
			comment.setGroupNum(comment.getIdx());
		}
		commentRepository.save(comment);
	}

	@Override
	public List<CommentGetDto> get(Long postId) {
		// TODO Auto-generated method stub
		log.info("PostId :"+postId);
		Post post = postRepository.getById(postId);
		List<Comment> comments = commentRepository.findByPost(post);
		List<CommentGetDto> lists = comments.stream()
				.map(comment -> new CommentGetDto(comment))
				.collect(Collectors.toList()); 
		return lists;
	}

}

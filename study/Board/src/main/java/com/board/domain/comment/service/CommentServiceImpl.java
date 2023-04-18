package com.board.domain.comment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event.ID;

import com.board.domain.comment.Comment;
import com.board.domain.comment.dto.CommentGetDto;
import com.board.domain.comment.dto.CommentInfoDto;
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
		log.info("save class... commentData.parentId ='"+commentData.getParentId()+"'");
		Comment parentComment ;
		if(commentData.getParentId()==null) {
			parentComment = null;
		}
		else {
			parentComment = commentRepository.findById(commentData.getParentId()).orElseThrow();
		}
		log.info("save class...'"+"'"); 
		Comment comment = commentData.toEntity(member, post,parentComment);
		commentRepository.save(comment);

	}

	@Override
	public List<CommentGetDto> get(Long postId) {
		// TODO Auto-generated method stub
		List<Comment> comments = commentRepository.findTopLevelCommentsOrderByCreatedAtDesc(postId);
		 
		List<CommentGetDto> lists = comments.stream()
		.peek(comment -> {if (comment.getDelete_yn().equals("Y")) {
                comment.setContent("삭제된 댓글입니다.");
            }})
		.map(comment -> new CommentGetDto(comment))
		.collect(Collectors.toList()); 
		
		for(CommentGetDto list : lists) {
			log.info("comment :'"+list.getDelete_yn()+"'");			
		}
		return lists;
	}

	@Override
	public void delete(Long idx) {
		// TODO Auto-generated method stub
		Comment c = commentRepository.findById(idx).orElseThrow();
		c.setDelete_yn("Y");
		commentRepository.save(c);		
	}

	@Override
	public Page<CommentInfoDto> getCommentList(String username, Pageable pageable) {
		// TODO Auto-generated method stub
		Member writer = memberRepository.findByUsername(username).orElseThrow();
		return 	commentRepository.findByWriterUsername(writer.getUsername(), pageable);
	}

}

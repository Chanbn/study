package com.board.domain.comment.dto;

import com.board.domain.comment.Comment;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentInfoDto {
	Long idx;
	Long postIdx;
	String username;
	String content;
	
	@Builder
	public CommentInfoDto(Comment comment) {
		this.username = comment.getWriter().getUsername();
		this.content = comment.getContent();
		this.idx = comment.getIdx();
		this.postIdx = comment.getPost().getIdx();
	}
}

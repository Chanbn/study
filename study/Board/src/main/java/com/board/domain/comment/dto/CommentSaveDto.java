package com.board.domain.comment.dto;

import com.board.domain.comment.Comment;
import com.board.domain.member.Member;
import com.board.domain.post.Post;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentSaveDto {
	Long postId;
	Long parentId;
	Long userId;
	String username;
	String content;
	
	@Builder
	public CommentSaveDto(Comment comment) {
		this.username = comment.getWriter().getUsername();
		this.content = comment.getContent();
		this.postId = comment.getPost().getIdx();
		this.parentId = comment.getParentComment() != null ? comment.getParentComment().getIdx() : null;
		this.userId = comment.getWriter().getId();
	}
	
	public Comment toEntity(Member member, Post post) {	
		return Comment.builder().
				content(content).parentComment(parentId != null ? Comment.builder().idx(parentId).build():null).
				writer(member).
				post(post).
				build();
	}
}

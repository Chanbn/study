package com.board.domain.comment;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.board.domain.BaseTimeEntity;
import com.board.domain.member.Member;
import com.board.domain.member.Role;
import com.board.domain.post.Post;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "board_comment")
@ToString 
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@Builder
public class Comment extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;
	
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member writer;
	
	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;
	
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Comment parentComment;
	
	@OneToMany(mappedBy = "parentComment")
	@OrderBy("createdDate ASC")
	private List<Comment> childComments = new ArrayList<>();
	
	@Builder 
	public Comment(String content,Member writer, Post post, Comment parentComment) {
		this.content = content;
		this.writer = writer;
		this.post = post;
		this.parentComment = parentComment;
	}
	
	public void setParentComment(Comment parentComment) {
		this.parentComment = parentComment;
	}
	
	public void addChildComment(Comment childComment) {
		this.childComments.add(childComment);
		childComment.setParentComment(this); 
	}
	
	public void setMember(Member writer) {
		this.writer= writer;
	}
	
	public void setPost(Post post) {
		this.post = post;
	}
}

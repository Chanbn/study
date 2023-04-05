package com.board.domain.comment;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.board.domain.BaseTimeEntity;
import com.board.domain.comment.dto.CommentSaveDto;
import com.board.domain.member.Member;
import com.board.domain.member.Role;
import com.board.domain.post.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Table(name = "board_comment")
@ToString 
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Slf4j
public class Comment extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_comment_seq")
	@SequenceGenerator(allocationSize = 1,name = "board_comment_seq",sequenceName = "board_comment_seq" )
	private Long idx;

	private String content;
	
	private Long groupNum;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member writer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Comment parentComment;

	
	@Builder 
	public Comment(String content,Member writer, Post post, Comment parentComment, Long groupNum) {
		this.content = content;
		this.writer = writer;
		this.post = post;
		this.parentComment = parentComment;
		if(groupNum == null) {
			log.info("groupNum=null -> this.idx ='"+this.idx+"'");
			this.groupNum = this.idx;
		}else {
			this.groupNum = groupNum;			
		}
	}
	
	
	public void setParentComment(Comment parentComment) {
		this.parentComment = parentComment;
		this.groupNum = parentComment.getGroupNum() == null ? this.idx : parentComment.getGroupNum();
	}
	
	
	public void setMember(Member writer) {
		this.writer= writer;
	}
	
	public void setPost(Post post) {
		this.post = post;
	}
	

}

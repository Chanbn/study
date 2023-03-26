package com.board.domain.post.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.board.domain.BaseTimeEntity;
import com.board.domain.comment.Comment;
import com.board.domain.comment.dto.CommentGetDto;
import com.board.domain.comment.dto.CommentSaveDto;
import com.board.domain.member.dto.MemberInfoDto;
import com.board.domain.post.Post;
import com.board.file.boardFile;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostInfoDto {
	private Long idx;
	private String content;
	private String title;
	private LocalDateTime createdDate;
	private List<boardFile> fileList;
	private MemberInfoDto writer;
	private List<CommentGetDto> comments;
	
	public PostInfoDto(Post post){
		
		this.idx = post.getIdx();
		this.content = post.getContent();
		this.title = post.getTitle();
		this.fileList = post.getFileLists();
		this.writer = new MemberInfoDto(post.getWriter()); 
		//post.getWriter -> Member object를 불러옴. Member object에서 MemberInfoDto에서 선언한 필드의 데이터들만 뽑아옴.
		this.comments = post.getComments().stream()
				.map(comment -> new CommentGetDto(comment))
				.collect(Collectors.toList());
		

		
	}
}

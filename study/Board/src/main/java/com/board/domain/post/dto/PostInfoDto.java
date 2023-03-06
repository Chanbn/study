package com.board.domain.post.dto;

import com.board.domain.member.dto.MemberInfoDto;
import com.board.domain.post.Post;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostInfoDto {
	private Long idx;
	private String content;
	private String title;
	
	private MemberInfoDto writer;
	
	public PostInfoDto(Post post){
		
		this.idx = post.getIdx();
		this.content = post.getContent();
		this.title = post.getTitle();
		
		
		this.writer = new MemberInfoDto(post.getWriter()); 
		//post.getWriter -> Member object를 불러옴. Member object에서 MemberInfoDto에서 선언한 필드의 데이터들만 뽑아옴.
		
		
	}
}

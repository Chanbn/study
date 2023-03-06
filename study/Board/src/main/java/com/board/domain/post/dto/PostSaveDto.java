package com.board.domain.post.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.board.domain.post.Post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostSaveDto {

	private Long idx;
	private String writer;
	private String content;
	private String title;
	private LocalDateTime regTime;
	private LocalDateTime updateTime;
	private String email;
	private String changeYn;
//	private List<Long> fileIdxs;
	private String deleteYn;
//	public MultipartFile[] files; 
	
	public PostSaveDto(String title, String content) {
		// TODO Auto-generated constructor stub
		this.title=title;
		this.content=content;
	}

	public Post toEntity() {
		return Post.builder().title(title).content(content).build();
		
	}
}

package com.board.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BoardDTO {

	private Long idx;
	private String writer;
	private String content;
	private String title;
	private LocalDateTime regTime;
	private LocalDateTime updateTime;
	private Long rnt;
	private Long good;
	private Long hate;
	private String email;
	private String changeYn;
	private List<Long> fileIdxs;
	private String deleteYn;
	public MultipartFile[] files; 
	private Long viewcount;
	 
}

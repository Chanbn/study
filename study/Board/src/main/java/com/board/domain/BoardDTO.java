package com.board.domain;

import java.time.LocalDateTime;

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
	 
}

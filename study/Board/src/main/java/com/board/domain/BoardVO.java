package com.board.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BoardVO {

	private Long idx;
	private String title;
	private String content;
	private String writer;
	private LocalDateTime reg_time;
	private LocalDateTime update_time;
	
}

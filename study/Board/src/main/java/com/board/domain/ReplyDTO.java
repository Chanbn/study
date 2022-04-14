package com.board.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyDTO {

	private String writer;
	private long bno;
	private long idx;
	private long parentBno;
	private LocalDateTime regDate;
	private String content;
}

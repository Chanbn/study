package com.board.domain;

import java.time.LocalDateTime;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyDTO {
	
	private long bno;
	private long idx;
	private String writer;
	private String email;
	private LocalDateTime regTime;
	private long parentBno;
	private String content;
	private long avail;
	private long groupnum;
}

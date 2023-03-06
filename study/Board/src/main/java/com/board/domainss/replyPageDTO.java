package com.board.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString

public class replyPageDTO {
	private int repCnt;
	private List<ReplyDTO> list;
	
	public replyPageDTO() {

	}
	public replyPageDTO(int repCnt, List<ReplyDTO> list) {
		this.repCnt=repCnt;
		this.list=list;
	}
	
}

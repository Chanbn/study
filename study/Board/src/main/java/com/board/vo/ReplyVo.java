package com.board.vo;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ReplyVo {

	@Id
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

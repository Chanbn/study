package com.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.ReplyDTO;

@Mapper
public interface ReplyMapper {
	public int write(ReplyDTO rp);
}

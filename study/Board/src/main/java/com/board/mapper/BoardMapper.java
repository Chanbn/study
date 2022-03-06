package com.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.BoardVO;

@Mapper
public interface BoardMapper {
	public int write(BoardVO vo);
	
}

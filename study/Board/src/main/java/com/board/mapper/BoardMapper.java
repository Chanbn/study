package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.BoardVO;

@Mapper
public interface BoardMapper {
	public int write(BoardVO vo);
	public List<BoardVO> getList();
	
}

package com.board.service;

import java.util.List;

import com.board.domain.BoardVO;


public interface BoardService {
	public int write(BoardVO vo);
	public List<BoardVO> getList();
}

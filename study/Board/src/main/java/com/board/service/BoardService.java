package com.board.service;

import java.util.List;

import com.board.domain.BoardVO;
import com.board.domain.Criteria;


public interface BoardService {
	public int write(BoardVO vo);
	public List<BoardVO> getList(Criteria cri);
	public int getTotal();
	public BoardVO get(long idx);
	public int modify(BoardVO vo);
	public int remove(long idx);
}

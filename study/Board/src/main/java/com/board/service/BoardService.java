package com.board.service;

import java.util.List;

import com.board.domain.BoardVO;
import com.board.domain.Criteria;


public interface BoardService {
	public int write(BoardVO vo);
	public List<BoardVO> getList(Criteria cri);
	public int getTotal(Criteria cri);
	public BoardVO get(long idx);
	public int modify(BoardVO vo);
	public int remove(long idx);
	public int rntcal(long rnt,long idx);
	public int chooseRating(Long idx, String writer,char choose);
}

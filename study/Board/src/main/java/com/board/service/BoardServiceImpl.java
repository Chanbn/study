package com.board.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.board.domain.BoardVO;
import com.board.mapper.BoardMapper;

public class BoardServiceImpl implements BoardService{

	@Autowired
	BoardMapper boardMapper;
	@Override
	public int write(BoardVO vo) {
		// TODO Auto-generated method stub
		int chk = boardMapper.write(vo);
		return chk;
	}


}

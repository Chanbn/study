package com.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.BoardVO;
import com.board.domain.Criteria;
import com.board.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	BoardMapper boardMapper;
	@Override
	public int write(BoardVO vo) {
		// TODO Auto-generated method stub
		int chk = boardMapper.write(vo);
		return chk;
	}
	@Override
	public List<BoardVO> getList(Criteria cri) {
		// TODO Auto-generated method stub
		return boardMapper.getList(cri);
	}
	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		return boardMapper.getTotalCount();
	}



}

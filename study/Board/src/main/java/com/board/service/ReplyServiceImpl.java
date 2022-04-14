package com.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.ReplyDTO;
import com.board.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	ReplyMapper mapper;
	
	@Override
	public int write(ReplyDTO rp) {
		// TODO Auto-generated method stub
		mapper.write(rp);
		return 0;
	}

}

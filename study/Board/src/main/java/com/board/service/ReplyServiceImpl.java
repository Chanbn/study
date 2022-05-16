package com.board.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.AuthVO;
import com.board.domain.Criteria;
import com.board.domain.ReplyDTO;
import com.board.domain.replyPageDTO;
import com.board.mapper.BoardMapper;
import com.board.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	ReplyMapper mapper;
	@Autowired
	BoardMapper boardmapper;
	
	@Override
	@Transactional
	public int write(ReplyDTO rp) {
		// TODO Auto-generated method stub
		boardmapper.updateReplyCnt(rp.getIdx(),1);
		return mapper.write(rp);
	}

	@Override
	public List<ReplyDTO> repList(long idx) {
		// TODO Auto-generated method stub
		return mapper.repList(idx);
	}

	@Override
	public List<ReplyDTO> repListWithpage(Criteria cri,long idx) {
		// TODO Auto-generated method stub
		return mapper.repListWithpage(cri,idx);
	}

	@Override
	public int repCount(long idx) {
		// TODO Auto-generated method stub
		return mapper.repCount(idx);
	}

	@Override
	public replyPageDTO getrepListpage(Criteria cri, long idx) {
		// TODO Auto-generated method stub
		return new replyPageDTO(mapper.repCount(idx),mapper.repListWithpage(cri, idx));
	}

	@Override
	public int findParent(long idx, long parentbno) {
		// TODO Auto-generated method stub
		return mapper.findParent(idx, parentbno);
	}

	@Override
	public AuthVO getProfile(String userid) {
		// TODO Auto-generated method stub
		return mapper.getProfile(userid);
	}

	@Transactional
	@Override
	public int replydelete(long idx, long bno) {
		// TODO Auto-generated method stub
		boardmapper.updateReplyCnt(idx,-1);
		return mapper.replydelete(idx, bno);
	}

}

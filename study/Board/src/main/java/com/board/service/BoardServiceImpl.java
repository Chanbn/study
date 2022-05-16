package com.board.service;

import java.util.List;

import javax.transaction.Transactional;

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
	public int getTotal(Criteria cri) {
		// TODO Auto-generated method stub
		return boardMapper.getTotalCount(cri);
	}
	@Override
	public BoardVO get(long idx) {
		// TODO Auto-generated method stub
		return boardMapper.get(idx);
	}
	@Override
	public int modify(BoardVO vo) {
		// TODO Auto-generated method stub
		return boardMapper.modify(vo);
	}
	@Override
	public int remove(long idx) {
		// TODO Auto-generated method stub
		return boardMapper.remove(idx);
	}
	@Override
	public int rntcal(long rnt, long idx) {
		// TODO Auto-generated method stub
		return boardMapper.rntcal(rnt, idx);
	}
	
	@Transactional
	@Override
	public int chooseRating(Long idx, String writer,char choose) {
		// TODO Auto-generated method stub
		int chk = boardMapper.boardRatingCheck(idx, writer);
		System.out.println("rating Check ::" +chk);
		if(chk==1) {
			return 3;
		}else {
			if(choose=='1') {
				boardMapper.boardRatingGood(idx);
			}else {
				boardMapper.boardRatingHate(idx);
			}
			return boardMapper.chooseRating(idx, writer,choose);
			
		}
	}

}

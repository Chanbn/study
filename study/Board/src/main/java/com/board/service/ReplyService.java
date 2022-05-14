package com.board.service;

import java.util.List;

import com.board.domain.AuthVO;
import com.board.domain.Criteria;
import com.board.domain.ReplyDTO;
import com.board.domain.replyPageDTO;

public interface ReplyService {
	public int write(ReplyDTO rp);
	public List<ReplyDTO> repList(long idx);
	public List<ReplyDTO> repListWithpage(Criteria cri,long idx);
	public int repCount(long idx);
	public replyPageDTO getrepListpage(Criteria cri,long idx);
	public int findParent(long idx, long parentbno);
	public AuthVO getProfile(String userid);
	public int replydelete(long idx, long bno);
}

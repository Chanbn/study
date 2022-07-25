package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.board.domain.AuthVO;
import com.board.domain.Criteria;
import com.board.domain.ReplyDTO;

@Mapper
public interface ReplyMapper {
	public int write(ReplyDTO rp);
	public List<ReplyDTO> repList(long idx);
	public List<ReplyDTO> repListWithpage(@Param("cri") Criteria cri,@Param("idx") long idx);
	public int repCount(long idx);
	public int findParent(@Param("idx") long idx,@Param("parentbno") long parentbno);
	public AuthVO getProfile(String userid);
	public int replydelete(@Param("idx") long idx, @Param("bno") long bno);
	public int AllreplyDelete(long idx);
}

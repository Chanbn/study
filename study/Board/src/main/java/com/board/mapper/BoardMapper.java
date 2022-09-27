package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.board.domain.BoardDTO;
import com.board.domain.BoardVO;
import com.board.domain.Criteria;
import com.board.domain.ReplyDTO;

@Mapper
public interface BoardMapper {
	public int write(BoardDTO vo);
	public List<BoardVO> getList(Criteria cri);
	public int getTotalCount(Criteria cri);
	public BoardDTO get(long idx);
	public int modify(BoardDTO vo);
	public int remove(long idx);
	public int rntcal(@Param("amount") long amount, @Param("idx") long idx);
	public int updateReplyCnt(@Param("idx") long idx,@Param("amount") int amount);
	public int chooseRating(@Param("idx") long idx, @Param("writer") String writer,@Param("choose") char choose);
	public int boardRatingGood(long idx);
	public int boardRatingHate(long idx);
	public int boardRatingCheck(@Param("idx") long idx, @Param("writer") String writer);
	public List<ReplyDTO> getCommentList(Criteria cri);
	public int getCommentCount(Criteria cri);
}

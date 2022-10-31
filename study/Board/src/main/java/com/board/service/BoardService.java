package com.board.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.board.domain.AttachDTO;
import com.board.domain.BoardDTO;
import com.board.domain.BoardVO;
import com.board.domain.Criteria;
import com.board.domain.ReplyDTO;


public interface BoardService {
	public boolean write(BoardDTO vo);
	public boolean write(BoardDTO vo, MultipartFile[] files);
	public boolean modify(BoardDTO vo);
	public boolean modify(BoardDTO vo, MultipartFile[] files);
	public List<BoardVO> getList(Criteria cri);
	public int getTotal(Criteria cri);
	public BoardDTO get(long idx);
	public int remove(long idx);
	public int rntcal(long rnt,long idx);
	public int chooseRating(Long idx, String writer,char choose);
	public List<AttachDTO> getAttachFileList(Long boardIdx);
	public AttachDTO getAttachDetail(Long idx);
	public List<ReplyDTO> getCommentList(Criteria cri);
	public int getCommentCount(Criteria cri);
}

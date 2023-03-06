package com.board.service;

import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.board.domain.AttachDTO;
import com.board.domain.BoardDTO;
import com.board.domain.BoardVO;
import com.board.domain.Criteria;
import com.board.domain.ReplyDTO;
import com.board.mapper.AttachMapper;
import com.board.mapper.BoardMapper;
import com.board.mapper.ReplyMapper;
import com.board.util.FileUtils;
import com.nimbusds.oauth2.sdk.util.CollectionUtils;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	BoardMapper boardMapper;
	@Autowired
	AttachMapper attachMapper;
	@Autowired
	ReplyMapper replyMapper;
	@Autowired
	private FileUtils fileUtils;
	
	@Override
	public boolean write(BoardDTO vo) {
		// TODO Auto-generated method stub
		int queryResult=0;
			queryResult = boardMapper.write(vo);
		return queryResult==1?true:false;
	}
	
	@Override
	public boolean write(BoardDTO vo, MultipartFile[] files) {
		// TODO Auto-generated method stub
		int queryResult = 1;
		if(write(vo)==false) {
			return false;
		}
		System.out.println("file Service 들어옴");


		List<AttachDTO> fileList = fileUtils.uploadFiles(files, vo.getIdx());
		System.out.println("파일갯수"+fileList.size());

		if(CollectionUtils.isEmpty(fileList)==false) {
			queryResult = attachMapper.insertAttach(fileList);
			System.out.println("queryResukt :: "+queryResult);
		}
		
		return queryResult==0?false:true;
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
	public BoardDTO get(long idx) {
		// TODO Auto-generated method stub
		return boardMapper.get(idx);
	}
	@Override
	public int remove(long idx) {
		// TODO Auto-generated method stub
		int resultValue=0;
		resultValue = boardMapper.remove(idx);
		if(resultValue==1) {
			attachMapper.deleteAttach(idx);
			replyMapper.AllreplyDelete(idx);
		}
		return boardMapper.remove(idx);
	}
	@Override
	public int rntcal(long rnt, long idx) {
		// TODO Auto-generated method stub
		return boardMapper.rntcal(rnt, idx);
	}
	

	@Override
	public List<AttachDTO> getAttachFileList(Long boardIdx) {
		// TODO Auto-generated method stub
		int fileTotalCount = attachMapper.selectAttachTotalCount(boardIdx);
		if(fileTotalCount<1) {
			return Collections.emptyList();
		}
		return attachMapper.selectAttachList(boardIdx);
	}

	@Override
	public AttachDTO getAttachDetail(Long idx) {
		// TODO Auto-generated method stub
		return attachMapper.selectAttachDetail(idx);
	}

	@Override
	public List<ReplyDTO> getCommentList(Criteria cri) {
		// TODO Auto-generated method stub
		return boardMapper.getCommentList(cri);
	}

	@Override
	public int getCommentCount(Criteria cri) {
		// TODO Auto-generated method stub
		return boardMapper.getCommentCount(cri);
	}

	@Override
	public boolean modify(BoardDTO vo) {
		// TODO Auto-generated method stub
		int queryResult = 0;
		queryResult = boardMapper.modify(vo);
		
		
		int chk = attachMapper.selectAttachTotalCount(vo.getIdx());
		
		if(chk>0) {
			attachMapper.deleteAttach(vo.getIdx());
		}
		
//		if("Y".equals(vo.getChangeYn())) {
//			attachMapper.deleteAttach(vo.getIdx());
//			if(CollectionUtils.isEmpty(vo.getFileIdxs())==false) {
//				attachMapper.undeleteAttach(vo.getFileIdxs());
//			}
//		}
		return queryResult==1 ? true : false;
	}

	@Override
	public boolean modify(BoardDTO vo, MultipartFile[] files) {
		// TODO Auto-generated method stub
		int queryResult = 1;
		if(modify(vo)==false) {
			return false;
		}
		System.out.println("file Service 들어옴");
		
		int chk = attachMapper.selectAttachTotalCount(vo.getIdx());
		
		if(chk>0) {
			attachMapper.deleteAttach(vo.getIdx());
		}

		List<AttachDTO> fileList = fileUtils.uploadFiles(files, vo.getIdx());
		System.out.println("파일갯수"+fileList.size());

		if(CollectionUtils.isEmpty(fileList)==false) {
			queryResult = attachMapper.insertAttach(fileList);
			System.out.println("queryResukt :: "+queryResult);
		}
		
		return queryResult==0?false:true;
	}

	@Override
	public Long updateViewcount(BoardDTO vo) {
		// TODO Auto-generated method stub
		return boardMapper.updateViewcount(vo);
	}



}

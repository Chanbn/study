package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.AttachDTO;

@Mapper
public interface AttachMapper {
	
	 public int insertAttach(List<AttachDTO> attach);
	 public AttachDTO selectAttachDetail(Long idx);
	 public List<AttachDTO> selectAttachList(Long boardIdx);
	 public int selectAttachTotalCount(Long boardIdx);
	 public int deleteAttach(Long boardIdx);
	 public int undeleteAttach(List<Long> idxs);
}

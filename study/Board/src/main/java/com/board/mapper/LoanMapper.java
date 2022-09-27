package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.LoanDTO;

@Mapper
public interface LoanMapper {

	public List<LoanDTO> getDataList();
	public int insertdata(LoanDTO loan);
}

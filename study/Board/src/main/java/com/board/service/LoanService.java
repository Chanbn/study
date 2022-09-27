package com.board.service;

import java.util.List;

import com.board.domain.LoanDTO;

public interface LoanService {
	public List<LoanDTO> getDataList();
	public int insertdata(LoanDTO loan);
}

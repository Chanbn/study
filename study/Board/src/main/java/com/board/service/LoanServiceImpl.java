package com.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.LoanDTO;
import com.board.mapper.LoanMapper;

@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	LoanMapper loanMapper;
	
	@Override
	public List<LoanDTO> getDataList() {
		// TODO Auto-generated method stub
		return loanMapper.getDataList();
	}

	@Override
	public int insertdata(LoanDTO loan) {
		// TODO Auto-generated method stub
		return loanMapper.insertdata(loan);
	}

}

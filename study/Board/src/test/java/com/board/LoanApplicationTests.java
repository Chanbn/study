package com.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.board.domain.LoanDTO;
import com.board.mapper.LoanMapper;
import com.board.service.LoanService;

@SpringBootTest
public class LoanApplicationTests {

	@Autowired
	LoanService loanMapper;

	@Test
	public void insertTeset() {
		LoanDTO loan = LoanDTO.builder().principal(1000000).rate(5).Totalno(60).build();
		

		loanMapper.insertdata(loan);
		
	}
}

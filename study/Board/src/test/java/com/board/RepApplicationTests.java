package com.board;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.PathVariable;

import com.board.domain.AuthVO;
import com.board.domain.ReplyDTO;
import com.board.service.ReplyService;

@SpringBootTest
public class RepApplicationTests {
	
	@Autowired
	ReplyService service;
	
	@Test
	public void readListTest() {
		AuthVO vo = service.getProfile("abc");
		System.out.println(vo.getUsername());
	}
}

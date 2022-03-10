package com.board;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.board.domain.BoardVO;
import com.board.domain.Criteria;
import com.board.mapper.BoardMapper;

@SpringBootTest
class BoardApplicationTests {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private SqlSessionFactory sessionFactory;

	@Test
	void contextLoads() {
	}

	@Test
	public void testByApplicationContext() {
		try {
			System.out.println("=========================");
			System.out.println(context.getBean("sqlSessionFactory"));
			System.out.println("=========================");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testBySqlSessionFactory() {
		try {
			System.out.println("=========================");
			System.out.println(sessionFactory.toString());
			System.out.println("=========================");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Autowired
	BoardMapper boardMapper;
	
	@Test
	public void mapperTest() {
		for(int i=1;i<=50;i++) {
			BoardVO vo = new BoardVO();
		vo.setTitle(i+"번 게시글 제목");
		vo.setWriter(i+"번 작성자");
		vo.setContent(i+"번 게시글 내용");
			int result = boardMapper.write(vo);
			System.out.println(result);
		}
	}
	
//	@Test
//	public void listmapperTest() {
//		List<BoardVO> vo = boardMapper.getList();
//		System.out.println(vo.get(0).getContent());
//	}
//	
	@Test
	public void listWithPageTest() {
		Criteria cri = new Criteria();
		List<BoardVO> vo = boardMapper.getList(cri);
		vo.forEach(board->System.out.println(board));
	}

}

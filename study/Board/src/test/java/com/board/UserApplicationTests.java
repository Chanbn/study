package com.board;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.board.domain.UserVO;
import com.board.mapper.UserMapper;

@SpringBootTest
public class UserApplicationTests {
	@Autowired
	private ApplicationContext context;

	@Autowired
	private SqlSessionFactory sessionFactory;
	
	@Autowired
	UserMapper userMapper;
	
	@Test
	public void getInfoTest() {
		UserVO vo;
		vo = userMapper.read("abc");
		vo.getAuthList().forEach(authVO->System.out.println(authVO));
	}
	
	@Test
	public void inPutData() {
		UserVO vo=new UserVO();
		vo.setUsername("abc");
		BCryptPasswordEncoder bcry = new BCryptPasswordEncoder();
		vo.setPassword(bcry.encode("1234"));
		System.out.println(userMapper.Signup(vo));
		System.out.println(userMapper.SignupAuth(vo.getUsername()));
	}
}

package com.board;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.board.domain.member.Member;
import com.board.domain.member.Role;
import com.board.domain.member.repository.MemberRepository;
import com.board.domain.member.service.MemberService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class memberServiceImplTest {
	@Autowired
	private EntityManager em;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Test
	public void 회원저장_성공() {
//		Member member = Member.builder().username("abc123123").password("123123").email("abc@email.com").nickname("nick").name("이름").role(Role.USER).build();
//		Member saveMember = memberRepository.save(member);
//		
//		Member findMember = memberRepository.findById(saveMember.getId()).orElseThrow(() -> new RuntimeException("저장된 회원이 없습니다."));
//		System.out.println(findMember.toString());
//		assertThat(findMember).isSameAs(saveMember);
//		assertThat(findMember).isSameAs(member);
		}
}

package com.board;



import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import com.board.domain.post.repository.PostRepository;
import com.board.domain.post.service.PostService;
import com.board.domain.member.Member;
import com.board.domain.member.Role;
import com.board.domain.member.dto.MemberSignUpDto;
import com.board.domain.member.repository.MemberRepository;
import com.board.domain.member.service.MemberService;
import com.board.domain.post.Post;
import com.board.domain.post.dto.PostSaveDto;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class postServiceImplTest {
@Autowired
private EntityManager em;



@Autowired
private PostService postService;

@Autowired
private PostRepository postRepository;

@Autowired
private MemberRepository memberRepository;

@Autowired
private MemberService memberService;

private String title = "제목";
private String content = "내용";

private String username = "aaa123";
private String password = "!qlalfqjsgh12";
private String nickname = "닉네임";
private String email = "eee@email.com";
private String name = "내이름";


private void clear(){
    em.flush();
    em.clear();
}

private List<Post> findPostList() {
	return em.createQuery("select p from Post p", Post.class).getResultList();
}

private Post findPost() {
	return em.createQuery("select p from Post p", Post.class).getSingleResult();
}


@BeforeEach
private void doSignupAndAuthentication() {
	memberService.signup(new MemberSignUpDto(username,nickname,name,password,email));
	SecurityContext emptyContext = SecurityContextHolder.createEmptyContext();

	emptyContext.setAuthentication(new UsernamePasswordAuthenticationToken(Member.builder().username(username).password(password).nickname(nickname).email(email).name(name).role(Role.USER).build(),null));
	SecurityContextHolder.setContext(emptyContext);
	clear();
}

@Test
public void writetest() {
	PostSaveDto postSaveDto = new PostSaveDto(title,content);

	for(int i=90;i<180;i++) {
		postSaveDto.setTitle(title+i);
		postSaveDto.setContent(content+i);
		postService.save(postSaveDto);
//		em.flush();
//		em.clear();
	}
//	List<Post> findPostList = findPostList();
//    Post post = em.find(Post.class, findPostList.get(6).getIdx());
//    assertThat(post.getContent()).isEqualTo(content);
}


}

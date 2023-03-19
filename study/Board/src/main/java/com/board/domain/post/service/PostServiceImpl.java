package com.board.domain.post.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.board.domain.post.dto.PostInfoDto;
import com.board.domain.post.dto.PostSaveDto;
import com.board.domain.post.exception.PostException;
import com.board.domain.post.exception.PostExceptionType;
import com.board.domain.post.repository.PostRepository;
import com.board.exception.BaseExceptionType;
import com.board.file.boardFile;
import com.board.file.dto.FileDto;
import com.board.file.service.FileService;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.board.domain.member.Member;
import com.board.domain.member.dto.MemberInfoDto;
import com.board.domain.member.exception.MemberException;
import com.board.domain.member.exception.MemberExceptionType;
import com.board.domain.member.repository.MemberRepository;
import com.board.domain.post.Post;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {
	private final PostRepository postRepository;
	private final MemberRepository memberRepository;
	private final FileService fileService;
	
@Override
@Transactional
public Long save(PostSaveDto postSaveDto) {
	// TODO Auto-generated method stub
	
	Post post = postSaveDto.toEntity();
	Member writer = memberRepository.findByUsername(postSaveDto.getUsername()).orElseThrow(()-> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));
	post.confirmWriter(writer);
	
    Post savedPost = postRepository.save(post); // Post 엔티티를 먼저 저장하고 반환받은 Post 엔티티
    if (postSaveDto.getFiles() != null) {
        List<boardFile> fileList = fileService.save(postSaveDto.getFiles(), savedPost.getIdx());
        for (boardFile file : fileList) {
            savedPost.addFile(file); // Post 엔티티에 첨부파일 추가
        }
    }
    return savedPost.getIdx();
}
@Override
public List<Post> getPageList(Pageable pageable) {
	// TODO Auto-generated method stub
	return postRepository.findAll();
}
@Override
public Page<Post> SearchPost(String type, String word, Pageable pageable) {
	// TODO Auto-generated method stub
	Page<Post> pageList = null;
	switch (type) {
	case "W":
		pageList = postRepository.findByWriterContaining(word, pageable);
		break;
	case "T":
		pageList = postRepository.findByTitleContaining(word, pageable);
		break;
	case "TC":
		pageList = postRepository.findByTitleContainingOrContentContaining(word,word,pageable);
		break;
	default:
		pageList = postRepository.findAll(pageable);
		break;
	}
	return pageList;
}
@Override
public PostInfoDto getPost(Long idx) {
	// TODO Auto-generated method stub
	PostInfoDto post = new PostInfoDto(postRepository.findByIdx(idx).orElseThrow(()->new PostException(PostExceptionType.WRONG_POST)));
	return post;
}




//@Override
//public Page<Post> findByTitleContaining(String title,Pageable pageable) {
//	// TODO Auto-generated method stub
//	Page<Post> pageList = postRepository.findByTitleContaining(title,pageable);
//	return pageList;
//}


}

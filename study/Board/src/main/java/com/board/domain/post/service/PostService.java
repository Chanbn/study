package com.board.domain.post.service;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.board.domain.post.Post;
import com.board.domain.post.dto.PostSaveDto;

public interface PostService {
	void save(String username,PostSaveDto postSaveDto);
	List<Post> getPageList(Pageable pageable);
//	Page<Post> findByTitleContaining(String title,Pageable pageable);
	Page<Post> SearchPost(String type, String word, Pageable pageable);

}

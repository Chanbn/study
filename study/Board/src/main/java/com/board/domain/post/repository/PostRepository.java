package com.board.domain.post.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.board.domain.comment.dto.CommentInfoDto;
import com.board.domain.post.Post;
import com.board.domain.post.dto.PostInfoDto;

public interface PostRepository extends JpaRepository<Post, Long>{

	Page<Post> findAll(Pageable pageable);
	Page<Post> findByTitleContaining(String title,Pageable pageable);
	Page<Post> findByTitleContainingOrContentContaining(String word, String word2, Pageable pageable);
	Page<Post> findByWriterUsernameContaining(String writer,Pageable pageable);
	Page<PostInfoDto> findByWriterUsername(String writer,Pageable pageable);
	
	Optional<Post> findByIdx(Long idx);
	

}

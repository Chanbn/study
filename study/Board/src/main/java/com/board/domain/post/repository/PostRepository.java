package com.board.domain.post.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.board.domain.post.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

	Page<Post> findAll(Pageable pageable);
	Page<Post> findByTitleContaining(String title,Pageable pageable);
	Page<Post> findByTitleContainingOrContentContaining(String word, String word2, Pageable pageable);
	Page<Post> findByWriterContaining(String writer,Pageable pageable);
	
	Optional<Post> findByIdx(Long idx);
	

}

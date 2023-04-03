package com.board.domain.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.board.domain.comment.Comment;
import com.board.domain.post.Post;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	public List<Comment> findByPost(Post post);
}

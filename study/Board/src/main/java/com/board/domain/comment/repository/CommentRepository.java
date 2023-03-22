package com.board.domain.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.board.domain.comment.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

}

package com.board.domain.comment.service;

import java.util.List;

import com.board.domain.comment.Comment;
import com.board.domain.comment.dto.CommentGetDto;
import com.board.domain.comment.dto.CommentSaveDto;

public interface CommentService {
	void save(CommentSaveDto commentData);
	void delete(Long idx);
	List<CommentGetDto> get(Long postId);
}

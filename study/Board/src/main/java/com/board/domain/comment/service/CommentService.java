package com.board.domain.comment.service;

import com.board.domain.comment.Comment;
import com.board.domain.comment.dto.CommentSaveDto;

public interface CommentService {
	void save(CommentSaveDto commentData);
}

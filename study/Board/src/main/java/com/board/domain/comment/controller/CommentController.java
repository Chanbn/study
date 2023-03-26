package com.board.domain.comment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.board.domain.comment.Comment;
import com.board.domain.comment.dto.CommentSaveDto;
import com.board.domain.comment.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/comment/*")
@RequiredArgsConstructor
public class CommentController {
	private final CommentService commentService;
	
	@PostMapping(value = "/add", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> writeComment(@RequestBody CommentSaveDto comment) {
		commentService.save(comment);
		return new ResponseEntity<>("success",HttpStatus.OK);
	}
}

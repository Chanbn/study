package com.board.domain.comment.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.board.domain.comment.Comment;
import com.board.domain.comment.dto.CommentGetDto;
import com.board.domain.comment.dto.CommentSaveDto;
import com.board.domain.comment.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/comment/*")
@RequiredArgsConstructor
@Slf4j
public class CommentController {
	private final CommentService commentService;
	
	@PostMapping(value = "/add", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> writeComment(@RequestBody CommentSaveDto comment) {
		log.info("comment : "+comment.getPostId() + comment.getUserId() + comment.getContent() + "parentNo : "+comment.getParentId());
		commentService.save(comment);
		return new ResponseEntity<>("success",HttpStatus.OK);
	}
	
	@GetMapping(value = "/{postId}")
	public ResponseEntity<List<CommentGetDto>> getComment(@RequestParam("postId") Long postId){
		log.info("postId : "+postId);
		return ResponseEntity.status(HttpStatus.OK).body(commentService.get(postId));
	}
}

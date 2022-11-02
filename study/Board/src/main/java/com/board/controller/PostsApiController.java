package com.board.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.board.domain.BoardDTO;
import com.board.domain.PostsDTO;
import com.board.domain.ReplyDTO;
import com.board.service.BoardService;

@RestController
@RequestMapping("/board")
public class PostsApiController {
	@Autowired
	BoardService service;
	

	@PostMapping(value = "/posts")
	public ResponseEntity<String> add(BoardDTO board) {
		boolean chk = false;
		if(board.getFiles()==null) {		//파일이 없는경우.
			chk = service.write(board);
		}else {			//파일이 있는경우.
			chk = service.write(board, board.getFiles());			
		}
		return chk==true? new ResponseEntity<>("success",HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping(value = "/posts/{idx}", consumes = "multipart/form-data")
	public ResponseEntity<String> modify(BoardDTO board){
		boolean chk = false;
		System.out.println(board.getIdx());
		
		if(board.getFiles()==null) {	//파일이 없는 경우.
			chk = service.modify(board);			
		}else {		//파일이 있는 경우.
			chk = service.modify(board, board.getFiles());
		}
		return chk==true? new ResponseEntity<>("success",HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping(value="/posts/{idx}")
	public ResponseEntity<String> delete(@RequestParam(value = "boardIdx") Long boardIdx){
	int chk = 0;
	chk = service.remove(boardIdx);
	return chk==1? new ResponseEntity<>("success",HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

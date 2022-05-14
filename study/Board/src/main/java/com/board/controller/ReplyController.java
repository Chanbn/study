package com.board.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.board.domain.AuthVO;
import com.board.domain.Criteria;
import com.board.domain.ReplyDTO;
import com.board.domain.replyPageDTO;
import com.board.service.ReplyService;

@RestController
@RequestMapping("/board")
public class ReplyController {
	@Autowired
	ReplyService service;

	/*
	 * @GetMapping("/{idx}") public ResponseEntity<List<ReplyDTO>>
	 * getReply(@PathVariable("idx") long idx,Model model) { List<ReplyDTO> rep =
	 * new ArrayList<>();
	 * 
	 * rep = service.repList(idx); return ResponseEntity.ok().body(rep); }
	 */

	  
	  @GetMapping("/{idx}/{page}") 
	  public ResponseEntity<replyPageDTO> getReplyList(@PathVariable("idx") long idx, @PathVariable("page") int page){
	  Criteria cri = new Criteria(page, 10); System.out.println(cri.getPageNum());

	  // return new ResponseEntity<replyPageDTO>(service.getrepListpage(cri,idx),HttpStatus.OK); 
	  return ResponseEntity.ok(service.getrepListpage(cri,idx)); }
	  
	 
	@PostMapping(value = "/new/", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> add(@RequestBody ReplyDTO rep) {
		System.out.println("here?");
		int nextBno = service.repCount(rep.getIdx())+1;
		System.out.println("next :: "+nextBno);
		rep.setBno(nextBno);
		if(rep.getGroupnum()==-1) {
			rep.setGroupnum(rep.getBno());
		}
		if(rep.getParentBno()!=0) {
		}
		/*
		 * if(rep.getBno()!=1&&rep.getParentBno()!=0) {
		 * 
		 * int nowParentBno = service.findParent(rep.getIdx(), rep.getParentBno());
		 * System.out.println("now !!:: " +nowParentBno); if(nowParentBno==0) {
		 * System.out.println("this is parentBno ::"+rep.getParentBno()); }else {
		 * rep.setParentBno(nowParentBno); }
		 * 
		 * }
		 */




		int chk = service.write(rep);
		return chk == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping(value="/{idx}/{bno}")
	public ResponseEntity<String> remove(@PathVariable("idx") long idx, @PathVariable("bno") int bno){
		
		int chk = service.replydelete(idx, bno);
		return chk==1? new ResponseEntity<>("success",HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

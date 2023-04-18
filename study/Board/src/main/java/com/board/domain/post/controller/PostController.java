package com.board.domain.post.controller;


import java.io.File;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.board.domain.member.dto.MemberInfoDto;
import com.board.domain.member.dto.MemberSessionDto;
import com.board.domain.post.dto.LastPageDto;
import com.board.domain.post.dto.PostInfoDto;
import com.board.domain.post.dto.PostSaveDto;
import com.board.domain.post.service.PostService;
import com.board.file.dto.FileDto;
import com.board.file.service.FileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Controller
@RequiredArgsConstructor
@RequestMapping("/board/*")
@Slf4j
public class PostController {
	
	private final PostService postService;
	private final FileService fileService;
	LastPageDto lastPage = new LastPageDto();
	
	
	@GetMapping(value = { "/list", "/myList" })
	public void getList(Model model, @PageableDefault(page = 0, size=10,sort = "createdDate", direction = Sort.Direction.DESC)Pageable pageable,@RequestParam(value = "type", defaultValue = "all") String type,@RequestParam(value = "", defaultValue = "") String word) {
		log.info("PostController - getList method--------------------------------------------------");
		Page<PostInfoDto> pageList =postService.SearchPost(type, word, pageable);
		lastPage.recentPageSet(pageable.getPageNumber());

		int startpage = 1;
		if(pageList.getNumber()!=1) {
			startpage = pageList.getNumber()-pageList.getNumber()%10+1;
		}
		System.out.println("currentPAge : "+pageList.getNumber());
		int endpage = pageList.getNumber()+(10-pageList.getNumber()%10);
		if(endpage>pageList.getTotalPages()) {
			endpage = pageList.getTotalPages();
		}
		boolean nextPage = endpage%10==0?true:false;
		
		model.addAttribute("boardList",pageList);
		model.addAttribute("prev",pageList.hasPrevious());
		model.addAttribute("next",nextPage);
		model.addAttribute("page",pageList.getNumber());
		model.addAttribute("startpage",startpage);
		model.addAttribute("endpage",endpage);
	}


	@GetMapping("/write")
	public String writeGet(Model model,@ModelAttribute("vo") PostSaveDto vo,Pageable pageable,@SessionAttribute("user") MemberSessionDto member) {
		log.info("PostController - writeGet method--------------------------------------------------");
		if (member == null) {
			log.info("로그인한 유저가 아님. redirect:/member/login ");
	        return "redirect:/member/login";
	    }
		
		MemberInfoDto memberinfoDto = new MemberInfoDto();
		memberinfoDto.setDto(member);
		vo.setWriter(memberinfoDto);
		model.addAttribute("vo", vo);

		List<FileDto> fileList = new ArrayList<>(); 
		model.addAttribute("fileList",fileList);
		return "board/write";
	}

	@ResponseBody
	@PostMapping(value = "/posts",consumes = "multipart/form-data")
	public ResponseEntity<String> add(PostSaveDto board, @SessionAttribute("user") MemberSessionDto member) {
		log.info("PostController - add method--------------------------------------------------");
		MemberInfoDto memberInfoDto = new MemberInfoDto();
		memberInfoDto.setDto(member);
		board.setWriter(memberInfoDto);
		Long number = postService.save(board);
		return number>=0? new ResponseEntity<>("success",HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/get")
	public void getPost(Model model, @RequestParam("idx") long idx) {
		log.info("PostController - getPost method--------------------------------------------------");
		PostInfoDto post = postService.getPost(idx);
		List<FileDto> fileList = fileService.getFileList(idx);
		model.addAttribute("post", post);
		model.addAttribute("fileList",fileList);
	}
	
	@GetMapping(value = "/download")
	public void downloadAttachFile(@RequestParam("idx") Long idx,Model model,HttpServletResponse response) {
		log.info("PostController - downloadAttachFile Method--------------------------------------------------");
		FileDto fileInfo = fileService.getFileDetails(idx);
		String uploadDate = fileInfo.getCreatedDate().format(DateTimeFormatter.ofPattern("yyMMdd"));
		String uploadPath = Paths.get("D:","SpringBootProject","upload",uploadDate).toString();
		String filename = fileInfo.getOriginalName();
		File file = new File(uploadPath,fileInfo.getSaveName());
		
		try {
			byte[] data = FileUtils.readFileToByteArray(file);
			response.setContentType("application/octet-stream");
			response.setContentLength(data.length);
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(filename, "UTF-8") + "\";");
			
			response.getOutputStream().write(data);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}catch (Exception e) {
			// TODO: handle exception
			
		}
	}
	
	@ResponseBody
	@DeleteMapping(value="/{boardIdx}")
	public ResponseEntity<String> deletePost(@RequestParam("boardIdx") Long boardIdx) {
		log.info("PostController - deletePost method--------------------------------------------------");
		log.info("boardIdx :'"+boardIdx+"'");
		postService.deletePost(boardIdx);
		return ResponseEntity.status(HttpStatus.OK).body("삭제가 완료되었습니다.");		
	}
	
	@PostMapping(value = "/edit")
	public void editPost(Model model, @RequestParam("boardIdx") Long boardIdx) {
		log.info("PostController editPost method--------------------------------------------------");
		log.info("post idx:'"+boardIdx+"'");
		PostInfoDto post = postService.getPost(boardIdx);
		log.info("username :'"+post.getWriter().getUsername()+"'");
		List<FileDto> fileList = fileService.getFileList(boardIdx);
		model.addAttribute("post", post);
		model.addAttribute("fileList",fileList);
	}
}

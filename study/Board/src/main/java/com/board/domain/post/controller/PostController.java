package com.board.domain.post.controller;


import java.net.URLEncoder;
import java.nio.file.Paths;
import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.hamcrest.text.IsEmptyString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.standard.expression.Each;

import com.board.domain.member.dto.MemberInfoDto;
import com.board.domain.member.dto.MemberSessionDto;
import com.board.domain.post.Post;
import com.board.domain.post.dto.Criteria;
import com.board.domain.post.dto.LastPageDto;
import com.board.domain.post.dto.PostSaveDto;
import com.board.domain.post.service.PostService;

import com.board.file.dto.FileDto;
import com.board.file.service.FileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Controller
@RequiredArgsConstructor
@RequestMapping("/board/*")
public class PostController {
	
	private final PostService postService;
	private final FileService fileService;
	LastPageDto lastPage = new LastPageDto();
	
	
	@GetMapping(value = { "/list", "/myList" })
	public void getList(Model model, @PageableDefault(page = 0, size=10, direction = Sort.Direction.DESC)Pageable pageable,@RequestParam(value = "type", defaultValue = "all") String type,@RequestParam(value = "", defaultValue = "") String word) {
		System.out.println("welcome!!");
		Page<Post> pageList =postService.SearchPost(type, word, pageable);
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
	public void writeGet(Model model,@ModelAttribute("vo") PostSaveDto vo,Pageable pageable,@SessionAttribute("user") MemberSessionDto member) {
		vo.setEmail(member.getEmail());
		vo.setWriter(member.getNickname());
		vo.setUsername(member.getUsername());
		model.addAttribute("vo", vo);
		model.addAttribute("cri",lastPage);

		List<FileDto> fileList = new ArrayList<>(); 
		model.addAttribute("fileList",fileList);
	}

	
	
//	@PostMapping("/write")
//	public void writePost(Model model,@ModelAttribute("vo") PostSaveDto vo) {
////		List<boardFile> fileList = fileService.save(files, vo.getIdx());
//		postService.save(vo,vo.getUsername());
//	}
//	
	@ResponseBody
	@PostMapping(value = "/posts")
	public ResponseEntity<String> add(PostSaveDto board) {
		Long number = postService.save(board);

		return number>=0? new ResponseEntity<>("success",HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}

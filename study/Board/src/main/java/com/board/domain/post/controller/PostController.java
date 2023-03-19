package com.board.domain.post.controller;


import java.io.File;
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
import com.board.domain.post.dto.PostInfoDto;
import com.board.domain.post.dto.PostSaveDto;
import com.board.domain.post.service.PostService;
import com.board.file.boardFile;
import com.board.file.dto.FileDto;
import com.board.file.service.FileService;
import com.board.file.service.FileServiceImpl;

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

	@ResponseBody
	@PostMapping(value = "/posts",consumes = "multipart/form-data")
	public ResponseEntity<String> add(PostSaveDto board) {
		Long number = postService.save(board);
		return number>=0? new ResponseEntity<>("success",HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/get")
	public void getPost(Model model, @RequestParam("idx") long idx) {
		PostInfoDto post = postService.getPost(idx);
		model.addAttribute("post", post);
		System.out.println(		"파일갯수"+post.getFileList().size());

	}
	
	@GetMapping(value = "/download")
	public void downloadAttachFile(@RequestParam("idx") Long idx,Model model,HttpServletResponse response) {
		boardFile fileInfo = fileService.getFileDetails(idx);
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
	
}

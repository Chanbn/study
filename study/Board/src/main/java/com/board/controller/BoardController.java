package com.board.controller;

import java.io.File;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.standard.expression.Each;

import com.board.domain.AttachDTO;
import com.board.domain.BoardDTO;
import com.board.domain.BoardVO;
import com.board.domain.Criteria;
import com.board.domain.PageDTO;
import com.board.domain.RatingVO;
import com.board.domain.user.User;
import com.board.mapper.BoardMapper;
import com.board.service.BoardService;


@EnableAspectJAutoProxy
@Controller
@RequestMapping("/board/*")
public class BoardController {
	@Autowired
	BoardService boardService;

	@GetMapping(value = { "/list", "/myList" })
	public void getList(Model model, @ModelAttribute("cri") Criteria cri) {
		model.addAttribute("boardList", boardService.getList(cri));
		System.out.println("keyword : " + cri.getKeyword() + " Email : " + " Type : " + cri.getType());
		int total = boardService.getTotal(cri);
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}

	@GetMapping(value = "/get")
	public void get(Model model, @ModelAttribute("cri") Criteria cri, @RequestParam("idx") int idx,
			@RequestParam("pageNum") int pageNum) {
		cri.setPageNum(pageNum);
		BoardDTO board = boardService.get(idx);
		model.addAttribute("board", board);
		List<AttachDTO> fileList = boardService.getAttachFileList(board.getIdx());
		model.addAttribute("fileList",fileList);
	}


	@GetMapping(value = "/write")
	public String write(Model model, @ModelAttribute("cri") Criteria cri, @ModelAttribute("vo") BoardDTO vo,
			@SessionAttribute("user") User user) {

			vo.setEmail(user.getEmail());
			vo.setWriter(user.getUsername());
			model.addAttribute("vo",vo);
			List<AttachDTO> fileList = new ArrayList<AttachDTO>();
			model.addAttribute("fileList",fileList);

		return "board/write";
	}
	
	@GetMapping(value = "/modify")
	public String modify(Model model, @ModelAttribute("cri") Criteria cri, @ModelAttribute("vo") BoardDTO vo,
			@SessionAttribute("user") User user)
	{
		BoardDTO board = boardService.get(vo.getIdx());
		if(board==null ||"Y".equals(board.getDeleteYn())) {
			return "redirect:/board/list?pageNum=2";
		}
		model.addAttribute("vo",board);
		
		List<AttachDTO> fileList = boardService.getAttachFileList(vo.getIdx());
		model.addAttribute("fileList",fileList);
		
		return "board/modify";
	}
	
	@GetMapping(value = "/download")
	public void downloadAttachFile(@RequestParam("idx") Long idx,Model model,HttpServletResponse response) {
		AttachDTO fileInfo = boardService.getAttachDetail(idx);
		String uploadDate = fileInfo.getInsertTime().format(DateTimeFormatter.ofPattern("yyMMdd"));
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
	/*
	 * @GetMapping(value = "/write", params = { "idx", "pageNum", "amount" }) public
	 * void modify(Model model, @RequestParam("idx") Long
	 * idx, @ModelAttribute("cri") Criteria cri) { long boardIdx = idx;
	 * List<AttachDTO> fileList = boardService.getAttachFileList(boardIdx);
	 * System.out.println("filesize" + fileList.toString());
	 * model.addAttribute("fileList", fileList); model.addAttribute("vo",
	 * boardService.get(idx));
	 * 
	 * }
	 */
	
	/*
	 * @PostMapping(value="/write") public void writeboard(@ModelAttribute("vo")
	 * BoardVO vo,Model model) { int chk = boardService.write(vo);
	 * System.out.println(chk); }
	 * 
	 */
	@PostMapping(value = "/write" )
	public String writeWithimage(@ModelAttribute("vo") BoardDTO vo, final MultipartFile[] files, Model model) {
		boolean chk = false;
		chk = boardService.write(vo, files);


		return "redirect:/board/get?pageNum=1";
}
	/*
	 * @GetMapping(value="/modify") public void modify1(Model
	 * model,@RequestParam("idx") Long idx,@ModelAttribute("cri") Criteria cri) {
	 * System.out.println(cri.getAmount()); long boardIdx = idx; List<AttachDTO>
	 * fileList = boardService.getAttachFileList(boardIdx);
	 * System.out.println("filesize"+fileList.toString());
	 * model.addAttribute("fileList",fileList);
	 * model.addAttribute("vo",boardService.get(idx));
	 * 
	 * }
	 */
	/*
	 * @PostMapping(value = "/modify") public String modify2(Model
	 * model, @ModelAttribute("vo") BoardDTO vo) {
	 * System.out.println(vo.toString()); boardService.modify(vo); return
	 * "redirect:/board/list?pageNum=1"; }
	 * 
	 */
	@GetMapping(value = "/remove")
	public String remove(Model model, @RequestParam("idx") int idx,RedirectAttributes rttr) {
		int chk = 0;
		chk = boardService.remove(idx);
		System.out.println("chk 값 ? "+chk);
		if(chk==1) {
			rttr.addFlashAttribute("msg", "삭제되었습니다.");			
		}else {
			rttr.addFlashAttribute("msg", "삭제오류");
		}
		return "redirect:/board/list?pageNum=1";
	}
	 
}

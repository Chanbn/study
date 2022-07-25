package com.board.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.board.domain.AttachDTO;
import com.board.exception.AttachFileException;

@Component
public class FileUtils {
	private final String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
	private final String uploadPath = Paths.get("D:","SpringBootProject","upload",today).toString();
	
	private final String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public List<AttachDTO> uploadFiles(MultipartFile[] files, Long boardIdx){
		
		List<AttachDTO> attachList = new ArrayList<>();
		
		File dir = new File(uploadPath);
		if(dir.exists()==false) {
			dir.mkdirs();
		}
		
		for(MultipartFile file:files){
			if(file.getSize()<1) {
				continue;
			}
			try {
				final String extension = FilenameUtils.getExtension(file.getOriginalFilename());
				final String saveName = getRandomString() + '.' + extension;
				
				File target = new File(uploadPath,saveName);
				file.transferTo(target);
				
				AttachDTO attach = new AttachDTO();
				attach.setBoardIdx(boardIdx);
				attach.setOriginalName(file.getOriginalFilename());
				attach.setSaveName(saveName);
				attach.setImageSize(file.getSize());
				System.out.println(attach.getOriginalName()+attach.getSaveName());
				attachList.add(attach);
			} catch (IOException e) {
				throw new AttachFileException("[" + file.getOriginalFilename() + "] failed to save file...");

			} catch (Exception e) {
				throw new AttachFileException("[" + file.getOriginalFilename() + "] failed to save file...");
			}
		}
		return attachList;
	}
}

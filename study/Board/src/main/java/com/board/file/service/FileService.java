package com.board.file.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.board.file.boardFile;
import com.board.file.dto.FileDto;

public interface FileService {
	List<boardFile> save(MultipartFile[] multipartFile,Long boardIdx);
	void delete(String filePath);
}

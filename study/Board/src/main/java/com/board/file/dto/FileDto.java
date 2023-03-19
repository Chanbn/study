package com.board.file.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDto {
	private Long idx;

	/** 게시글 번호 (FK) */
	private Long boardIdx;

	/** 원본 파일명 */
	private String originalName;

	/** 저장 파일명 */
	private String saveName;

	/** 파일 크기 */
	private long imageSize;
	
	private String deleteYn;
	
	private LocalDateTime createdDate; 
}

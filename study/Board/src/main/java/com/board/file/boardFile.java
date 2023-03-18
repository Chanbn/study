package com.board.file;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.board.domain.post.Post;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
//@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "attach")
@Entity
public class boardFile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idx;

//	/** 게시글 번호 (FK) */
//	private Long boardIdx;
 
	/** 원본 파일명 */
	private String originalName;

	/** 저장 파일명 */
	private String saveName;

	/** 파일 크기 */
	private long imageSize;
	
	private String deleteYn;
	
	private LocalDateTime insertTime;
	private LocalDateTime deleteTime;
	 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "file_id")
	private Post postFiles;
	
	public void addFilese(Post postFiles) {
		this.postFiles = postFiles;
		postFiles.addFile(this);
	}
}

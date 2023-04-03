package com.board.domain.post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

import com.board.domain.BaseTimeEntity;
import com.board.domain.comment.Comment;
import com.board.domain.member.Member;
import com.board.file.boardFile;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Table(name = "board")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@ToString
public class Post extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idx;

	private String title;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "writer_id")
	private Member writer;
	
	private String content;
	@Column(nullable = false)
	private String email;
	private String deleteYn;
	 
	  @Builder
	    public Post(String title, String content) {
	        this.title = title;
	        this.content = content;
	    }
	  
	  @OneToMany(mappedBy = "post")
	  private List<boardFile> fileLists = new ArrayList<>();
	  
	  public void addFile(boardFile files)
	  {
		  fileLists.add(files);
		  files.setPost(this);
	  }
	  
	  @OneToMany(mappedBy = "post")
	  private List<Comment> comments = new ArrayList<>();
	  
	  public void addComment(Comment comment) {
		  comments.add(comment);
		  comment.setPost(this);		  
	  }
	  
    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }
	
    public void confirmWriter(Member writer) {
    	this.writer = writer;
    	writer.addPost(this);
    }

  
	 
}

package com.board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostsDTO {
	String writer;
	String email;
	String content;
	String title;
}

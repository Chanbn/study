package com.board.domain.post.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
private int pageNum;
private int amount;
private String keyword;
private String type;

public Criteria() {
	this(1,10);
}
public Criteria(int pageNum, int amount) {
	this.pageNum = pageNum;
	this.amount = amount;
}

@Builder
public Criteria(String keyword, String type) {
	this.keyword=keyword;
	this.type=type;
}


public String[] getTypeArr() {
	
	return type == null ? new String[] {}: type.split(""); 
}
}

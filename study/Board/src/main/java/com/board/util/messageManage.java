package com.board.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class messageManage {
public String message;
public String url;
public messageManage(String message,String url) {
	this.message = message;
	this.url = url;
	
}
}

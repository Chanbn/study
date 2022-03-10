package com.board.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
	private boolean prev,next;
	private int endPage;
	private int startPage;
	
	private int total;
	private Criteria cri;
	public PageDTO(Criteria cri,int total) {
		this.cri = cri;
		this.total=total;
		
		this.endPage=(int)Math.ceil(cri.getPageNum()/10.0)*10;	//현재 페이지 번호를 불러와서 endpage를 구한다.
		this.startPage = this.endPage-9;						//현재 페이지 번호에서의 startpage를 구한다.
		
		int realEnd = (int)Math.ceil((this.total*1.0)/cri.getAmount());
		if(realEnd<this.endPage) {
			this.endPage=realEnd ; 
		}
		
		this.prev = this.startPage>1;
		this.next = this.endPage<realEnd;
	}
	

}



package com.board.domain.store;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class storePageDTO {
	private List<Store> list;
	

	
	public storePageDTO(List<Store> list){
		this.list = list;
	}
	
}

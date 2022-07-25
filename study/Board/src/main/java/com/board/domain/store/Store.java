package com.board.domain.store;

import java.util.List;

import com.board.domain.AuthVO;

import lombok.Data;


@Data
public class Store {
	private String name;
	private String storesectors;
	private String address;
	private String address1;
	private String address2;
	private String address3;
	private int address4;
	private float latitude;
	private float longitude;
}

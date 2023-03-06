package com.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.store.Store;
import com.board.domain.store.storePageDTO;
import com.board.mapper.StoreMapper;

import lombok.AllArgsConstructor;

@Service
public class StoreServiceImpl implements StoreService {
	
	@Autowired
	private StoreMapper mapper;

	@Override
	public List<Store> StoreInfo(String storesectors) {
		// TODO Auto-generated method stub
		return mapper.StoreInfo(storesectors);
	}

	@Override
	public storePageDTO StoreInfoList(String storesectors) {
		// TODO Auto-generated method stub
		List<Store> list = mapper.StoreInfo(storesectors);
		System.out.println(list.size());
		return new storePageDTO(mapper.StoreInfo(storesectors));
	}

}

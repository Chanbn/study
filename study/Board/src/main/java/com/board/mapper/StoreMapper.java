package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.store.Store;
import com.board.domain.store.storePageDTO;

@Mapper
public interface StoreMapper {
	public List<Store> StoreInfo(String storesectors);
	public storePageDTO StoreInfoList(String storesectors);
}

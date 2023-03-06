package com.board.service;

import java.util.List;

import com.board.domain.store.Store;
import com.board.domain.store.storePageDTO;

public interface StoreService {
public List<Store> StoreInfo(String storesectors);
public storePageDTO StoreInfoList(String storesectors);
}

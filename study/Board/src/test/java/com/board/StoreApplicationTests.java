package com.board;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.board.domain.store.Store;
import com.board.domain.store.storePageDTO;
import com.board.mapper.StoreMapper;

import lombok.AllArgsConstructor;


@SpringBootTest
public class StoreApplicationTests {
	
	@Autowired
	private StoreMapper mapper;
	
	@Test
	public void infoTest() {
		String storesectors = "주유";
		List<Store> storeObj = mapper.StoreInfo(storesectors);
		storePageDTO spd = new storePageDTO(storeObj);
		spd.getList().forEach(oo->System.out.println(oo.getName()));
	}
}

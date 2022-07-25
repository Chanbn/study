package com.board.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.domain.store.Store;
import com.board.domain.store.storePageDTO;
import com.board.service.StoreService;

@Controller
@RequestMapping("/findMap")
public class StoreController {
	
	@Autowired
	private StoreService service;
	
	@GetMapping("/home")
	public void home() {
		
	}
	
	@ResponseBody
	@GetMapping("/home/{checkValue}")
	public ResponseEntity<storePageDTO> requestMap(@PathVariable("checkValue") String checkValue){
		System.out.println("ASD");
		return ResponseEntity.ok(service.StoreInfoList(checkValue));

	}
}

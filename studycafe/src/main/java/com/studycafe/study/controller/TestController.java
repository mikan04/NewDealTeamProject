package com.studycafe.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class TestController {
	
	// 마커 불러오기
	@GetMapping(value = "/view")
	public String markerView() {
		
		return "index";
	}	
	
	// 마커 등록폼
	@GetMapping(value = "/insert")
	public String marKerInsertForm() {
		
		return "index2";
	}
}

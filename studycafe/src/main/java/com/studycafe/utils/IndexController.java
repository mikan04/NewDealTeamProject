package com.studycafe.utils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IndexController {
	
	@GetMapping("/")
	public String 접속컨트롤러() {
		log.info("메인페이지 접속");
		return "index";
	}
}

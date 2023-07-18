package com.studycafe.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudyController {
	
	@GetMapping("/studyregis")
	public String studyRegis() {
		return "/study/studyregistration";
	}
}

package com.studycafe.team.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TeamController {
	
	@GetMapping("/team/teamboard")
	public String teamList() {
		log.info("팀 등록 게시판 접속");
		
		return "/team/teamboard";
	}
	
}

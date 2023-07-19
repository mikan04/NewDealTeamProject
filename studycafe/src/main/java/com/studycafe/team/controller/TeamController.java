package com.studycafe.team.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TeamController {
	
	// 게시판 접속
	@GetMapping("/team/teamboard")
	public String teamList() {
		log.info("팀 등록 게시판 접속");
		
		return "/team/teamboard";
	}
	
	// 글등록
	@GetMapping("/team/teamregis")
	public String teamRegist() {
		log.info("팀 등록 글 작성");
		
		return "/team/teamregis";
	}
}

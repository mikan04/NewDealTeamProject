package com.studycafe.team.teamboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.studycafe.team.teamboard.entity.TeamBoardEntity;
import com.studycafe.team.teamboard.service.TeamBoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TeamBoardController {

	@Autowired
	private TeamBoardService teamBoardService;

	// 게시판 접속
	@GetMapping("/team/teamboard")
	public String teamList() {
		log.info("팀 등록 게시판 접속");

		return "/team/teamboard";
	}

	// 글등록 페이지
	@GetMapping("/team/teamregispage")
	public String teamRegistPage() {
		log.info("팀 등록 글 작성");

		return "/team/teamregis";
	}

	// 글 등록 로직 - 잠깐 테스트용
	@PostMapping("/team/teamregis")
	public String teamRegist(@RequestParam("file") MultipartFile file, TeamBoardEntity teamBoard) {	
		
		teamBoardService.teamBoardRegis(file, teamBoard);
		
		return "redirect:/team/teamboard";
	}
}

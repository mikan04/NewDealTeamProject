package com.studycafe.team.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.studycafe.utils.file.service.S3Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TeamController {
	
	@Autowired
	private S3Service s3Service;
	
	// S3 경로변수
	private final String path = "team";
	
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
	@ResponseBody
	public String teamRegist(@RequestParam("file") MultipartFile file) {
		log.info("팀 등록 글 작성로직 실행");
		
		s3Service.saveFile(file, path);
		
		log.info("파일 업로드 완료");
		
		return teamList();
	}

}

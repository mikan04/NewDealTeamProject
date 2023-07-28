package com.studycafe.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.studycafe.member.entity.MemberAdaptor;
import com.studycafe.study.service.StudyService;
import com.studycafe.team.teamboard.service.TeamBoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IndexController {
	
	@Autowired
	private StudyService studyService;
	
	@Autowired
	private TeamBoardService teamBoardService;
	
	@GetMapping("/")
	public String 접속컨트롤러(@AuthenticationPrincipal MemberAdaptor memberAdaptor, Model model, Pageable pageable) {
		
		log.info("메인페이지 접속");
		
		if (memberAdaptor != null) {
			log.info("회원 접속");
			
			log.info(memberAdaptor.getUsername());
			log.info(memberAdaptor.getPassword()); // 시큐리티로 인해 null출력
			log.info("현재 유저의 권한 : {}" , memberAdaptor.getAuthorities());
			log.info("memberAdaptor.getMember() : {}" , memberAdaptor.getMember());
			
		} else {
			log.info("비회원 접속");
		}
		
		model.addAttribute("studyList", studyService.getAllStudyToIndex());
		model.addAttribute("teamBoardList", teamBoardService.getTeamBoardListToIndex());
		
		return "index";
	}
}

package com.studycafe.team.teamboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.studycafe.team.teamboard.dto.TeamBoardDTO;
import com.studycafe.team.teamboard.service.TeamBoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TeamBoardController {

	@Autowired
	private TeamBoardService teamBoardService;

	// 게시판 접속 및 게시글 리스트 및 페이징
	@GetMapping("/team/teamboard")
	public String teamList(Model model) {
		log.info("팀 등록 게시판 접속");

		model.addAttribute("teamBoardList", teamBoardService.getTeamBoardList());

		return "/team/teamboard";
	}

	// 글등록 페이지
	@GetMapping("/team/teamregispage")
	public String teamRegistPage() {
		log.info("팀 등록 글 작성");

		return "/team/teamregis";
	}

	// 글 등록 로직
	@PostMapping("/team/teamregis")
	public String teamRegist(TeamBoardDTO teamBoardDTO) {

		teamBoardService.teamBoardRegis(teamBoardDTO);

		return "redirect:/team/teamboard";
	}

	// 글 조회
	@GetMapping("/teamboard/{idx}")
	public String getTeamBoardPost(@PathVariable("idx") int idx, Model model) {

		TeamBoardDTO getPost = teamBoardService.getTeamBoardPost(idx);

		model.addAttribute("teamPost", getPost);

		return "/team/detailview";

	}

}

package com.studycafe.team.teamboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.studycafe.team.teamboard.dto.TeamBoardDTO;
import com.studycafe.team.teamboard.entity.TeamBoardEntity;
import com.studycafe.team.teamboard.service.TeamBoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TeamBoardController {

	@Autowired
	private TeamBoardService teamBoardService;

	// 게시판 접속 및 게시글 리스트 불러오기
	@GetMapping("/team/teamboards")
	public String teamList(@PageableDefault(size = 3, sort="teamBoardNum", direction = Sort.Direction.DESC) Pageable pageable , Model model) {
		log.info("팀 등록 게시판 접속");

		model.addAttribute("teamBoardList", teamBoardService.getTeamBoardList());

		return "/team/teamboard";
	}
	
	// 페이징
	@GetMapping("/team/teamboard/page")
	public List<TeamBoardEntity> pageList(@PageableDefault(size = 3, sort="teamBoardNum", direction = Sort.Direction.DESC) Pageable pageable){
		
		Page<TeamBoardEntity> contentPaging = teamBoardService.getPageList(pageable);
		
		List<TeamBoardEntity> boardContent = contentPaging.getContent();
		
		return boardContent;
	}

	// 글등록 페이지
	@GetMapping("/team/teamregispage")
	public String teamRegistPage() {
		log.info("팀 등록 글 작성 페이지");

		return "/team/teamregis";
	}

	// 글 등록 로직
	@PostMapping("/team/teamregis")
	public String teamRegist(TeamBoardDTO teamBoardDTO) {
		
		log.info("팀 등록 실행");
		
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

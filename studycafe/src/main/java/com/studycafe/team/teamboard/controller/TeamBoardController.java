package com.studycafe.team.teamboard.controller;

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
import com.studycafe.team.teamboard.dto.TeamBoardPageDTO;
import com.studycafe.team.teamboard.service.TeamBoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TeamBoardController {

	@Autowired
	private TeamBoardService teamBoardService;

	// 게시판 접속 및 게시글 리스트 불러오기 및 페이징
	@GetMapping("/team/teamboards")
	public String teamList(
			@PageableDefault(page = 0, size = 3, sort = "teamBoardNum", direction = Sort.Direction.DESC)
			Pageable pageable,
			TeamBoardPageDTO teamBoardPage,
			Model model) {
		
		log.info("팀 등록 게시판 접속");
		
		// 게시글 리스트
		Page<TeamBoardDTO> teamBoardDTOList = teamBoardService.getTeamBoardList(pageable);
		
		// 페이징 로직
		TeamBoardPageDTO page = teamBoardPage.convertPageDTO(teamBoardDTOList);
		
		page.paging();
		
		log.info("페이징 getNumber : {}", page.getNumber());
		log.info("페이징 getEndPage : {}", page.getEndPage());
		log.info("페이징 getStartPage : {}", page.getStartPage());
	
		model.addAttribute("teamBoardList", teamBoardDTOList);
		model.addAttribute("page", page);

		return "/team/teamboard";
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

		log.info("뭔가이상함 : {}", teamBoardDTO);
		
		teamBoardService.teamBoardRegis(teamBoardDTO);

		return "redirect:/team/teamboards";
	}

	// 글 조회
	@GetMapping("/teamboard/{idx}")
	public String getTeamBoardPost(@PathVariable("idx") int idx, Model model) {

		TeamBoardDTO getPost = teamBoardService.getTeamBoardPost(idx);

		model.addAttribute("teamPost", getPost);

		return "/team/detailview";

	}

}

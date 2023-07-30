package com.studycafe.team.teamboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studycafe.member.entity.MemberAdaptor;
import com.studycafe.team.teamboard.dto.TeamBoardDTO;
import com.studycafe.team.teamboard.dto.TeamBoardPageDTO;
import com.studycafe.team.teamboard.entity.TeamBoardEntity;
import com.studycafe.team.teamboard.repository.TeamBoardRepository;
import com.studycafe.team.teamboard.service.TeamBoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TeamBoardController {

	@Autowired
	private TeamBoardService teamBoardService;

	@Autowired
	private TeamBoardRepository teamBoardRepository;

	// 게시판 접속 및 게시글 리스트 불러오기 및 페이징
	@GetMapping("/team/teamboards")
	public String teamList(
			@PageableDefault(page = 0, size = 3, sort = "teamBoardNum", direction = Sort.Direction.DESC) Pageable pageable,
			TeamBoardPageDTO teamBoardPage,
			Model model) {

		log.info("팀 등록 게시판 접속");

		// 게시글 리스트
		Page<TeamBoardDTO> teamBoardDTOList = teamBoardService.getTeamBoardList(pageable);

		// 페이징 로직
		TeamBoardPageDTO page = teamBoardPage.convertPageDTO(teamBoardDTOList);

		page.paging();

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

		teamBoardService.teamBoardRegis(teamBoardDTO);

		return "redirect:/team/teamboards";
	}

	// 글 조회
	@GetMapping("/team/teamboard/{idx}")
	public String getTeamBoardPost(@PathVariable("idx") int idx, Model model) {

		TeamBoardDTO getPost = teamBoardService.getTeamBoardPost(idx);

		model.addAttribute("teamPost", getPost);

		return "/team/detailview";

	}

	// 글 수정 페이지 진입
	@GetMapping("/team/modifyview/{idx}")
	public String modifyTeamBoardPostPage(@PathVariable("idx") long idx, Model model, @AuthenticationPrincipal MemberAdaptor memberAdaptor) {

		TeamBoardDTO getPost = teamBoardService.getTeamBoardPost(idx);

		TeamBoardEntity getWriter = teamBoardRepository.findById(idx).get();

		if (memberAdaptor == null) {
			
			throw new AccessDeniedException("남의 게시글을 함부러 수정하려고 하시면 안돼요!!");
			
		} else {
			
			String teamBoardWriter = getWriter.getTeamBoardWriter();

			String loginedUserNickName = memberAdaptor.getMember().getNickName();

			if (!teamBoardWriter.equals(loginedUserNickName)) {

				throw new AccessDeniedException("남의 게시글을 함부러 수정하려고 하시면 안돼요!!");

			}
		}

		model.addAttribute("teamPost", getPost);

		return "/team/modifyview";

	}

	// 글 수정
	@PatchMapping("/team/modifyview/{idx}")
	public String modifyTeamBoard(TeamBoardDTO teamBoardDTO) {

		teamBoardService.teamBoardRegis(teamBoardDTO);

		return "redirect:/team/teamboard/{idx}";

	}

	// 글 삭제
	@ResponseBody
	@DeleteMapping("/team/removepost")
	public boolean deleteTeamBoard(@RequestParam("idx") long idx) {
		
		try {
			boolean remove = teamBoardService.deleteTeamBoard(idx);
			
			return remove;
			
		} catch (Exception e) {
			throw new IllegalArgumentException("존재하지 않는 게시물입니다.");
			
		}
	}
}

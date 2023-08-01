package com.studycafe.cs.controller;

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

import com.studycafe.cs.dto.CsBoardDTO;
import com.studycafe.cs.dto.CsBoardPageDTO;
import com.studycafe.cs.entity.CsEntity;
import com.studycafe.cs.repository.CsRepository;
import com.studycafe.cs.service.CsService;
import com.studycafe.member.entity.MemberAdaptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CsController {
	@Autowired
	private CsService csService;

	@Autowired
	private CsRepository csRepository;

	// 게시판 접속 및 게시글 리스트 불러오기 및 페이징
	@GetMapping("/cs/csboard")
	public String teamList(
			@PageableDefault(page = 0, size = 3, sort = "idx", direction = Sort.Direction.DESC) Pageable pageable,
			CsBoardPageDTO csBoardPage,
			Model model) {

		log.info("고객센터 접속");

		// 게시글 리스트
		Page<CsBoardDTO> csBoardDTOList = csService.getCsBoardList(pageable);

		// 페이징 로직
		CsBoardPageDTO page = csBoardPage.convertPageDTO(csBoardDTOList);

		page.paging();

		model.addAttribute("csBoardList", csBoardDTOList);
		model.addAttribute("page", page);

		return "/cs/csboard";
	}

	// 글등록 페이지
	@GetMapping("/cs/csregispage")
	public String teamRegistPage() {
		log.info("팀 등록 글 작성 페이지");

		return "/cs/csregis";
	}

	// 글 등록 로직
	@PostMapping("/cs/csregis")
	public String csRegist(CsBoardDTO csBoardDTO) {

		csService.csBoardRegis(csBoardDTO);

		return "redirect:/cs/csboards";
	}

	// 글 조회
	@GetMapping("/cs/csboard/{idx}")
	public String getCsBoardPost(@PathVariable("idx") int idx, Model model) {

		CsBoardDTO getPost = csService.getCsBoardPost(idx);

		model.addAttribute("csPost", getPost);

		return "/cs/detailview";

	}

	// 글 수정 페이지 진입
	@GetMapping("/cs/modifyview/{idx}")
	public String modifyTeamBoardPostPage(@PathVariable("idx") long idx, Model model, @AuthenticationPrincipal MemberAdaptor memberAdaptor) {

		CsBoardDTO getPost = csService.getCsBoardPost(idx);

		CsEntity getWriter = csRepository.findById(idx).get();

		if (memberAdaptor == null) {

			throw new AccessDeniedException("남의 게시글을 함부러 수정하려고 하시면 안돼요!!");

		} else {

			String csBoardWriter = getWriter.getCsWriter();

			String loginedUserNickName = memberAdaptor.getMember().getNickName();

			if (!csBoardWriter.equals(loginedUserNickName)) {

				throw new AccessDeniedException("남의 게시글을 함부러 수정하려고 하시면 안돼요!!");

			}
		}

		model.addAttribute("csPost", getPost);

		return "/cs/modifyview";

	}

	// 글 수정
	@PatchMapping("/cs/modifyview/{idx}")
	public String modifyTeamBoard(CsBoardDTO csBoardDTO) {

		csService.csBoardRegis(csBoardDTO);

		return "redirect:/cs/csboard/{idx}";

	}

	// 글 삭제
	@ResponseBody
	@DeleteMapping("/cs/removepost")
	public boolean deleteTeamBoard(@RequestParam("idx") long idx) {

		try {
			boolean remove = csService.deleteCsBoard(idx);

			return remove;

		} catch (Exception e) {
			throw new IllegalArgumentException("존재하지 않는 게시물입니다.");

		}
	}
}

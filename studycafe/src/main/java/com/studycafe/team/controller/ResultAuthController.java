package com.studycafe.team.controller;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studycafe.member.auth.PrincipalDetails;
import com.studycafe.team.entity.ResultAuthEntity;
import com.studycafe.team.entity.TeamEntity;
import com.studycafe.team.repository.TeamRepository;
import com.studycafe.team.service.ResultAuthService;
import com.studycafe.team.service.TeamService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ResultAuthController {

	@Autowired
	private ResultAuthService resultAuthService;
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private TeamRepository teamRepository;
	
	@GetMapping("/auth")
	public String resultAuthList(@AuthenticationPrincipal PrincipalDetails principalDetails , Model model) {
		
		TeamEntity teamHead = teamRepository.findByTeamHead(principalDetails.getUsername());
		
		model.addAttribute("teamHead", teamHead);
		
		return "/resultauth/resultauthlist";
	}
	
	@GetMapping("/authlist")
	@ResponseBody
	public Page<ResultAuthEntity> resultAuthListAjax(
			@PageableDefault(page = 0, size = 10, sort = "resultAuthNum", direction = Sort.Direction.DESC) Pageable pageable,
			@RequestParam(required = false) String keyword) {

		Page<ResultAuthEntity> list = null;
		
		if (keyword == null) {
			list = resultAuthService.resultAuthList(pageable);
		} else {
			list = resultAuthService.resultAuthSearchList(keyword, pageable);
		}

		int nowPage = list.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, list.getTotalPages());
		
		return list;
	}
	
	// 인증 게시글 폼
	@GetMapping("/auth/registration")
	public String resultAuthRegistraion() {
		
		return "/resultauth/resultauthregistration";
	}
	
	// 인증 게시글 작성
	@PostMapping("/auth/registrationpro")
	public String resultAuthRegistraionPro(ResultAuthEntity resultAuthEntity) throws Exception {
		
		resultAuthService.resultAuthInsert(resultAuthEntity);

		return "/resultauth/resultauthlist";
	}
	
	// 인증 게시글 디테일
	@GetMapping("/auth/detail/{no}")
	public String resultAuthDetail(@PathVariable("no") int id, Model model) {
		
		ResultAuthEntity resultAuthEntity = new ResultAuthEntity();
		
		resultAuthEntity = resultAuthService.resultAuthSelect(id);
		
		model.addAttribute("resultAuthEntity", resultAuthEntity);
		
		
		return "/resultauth/resultauthdetail";
	}
	
	// 인증 게시글 삭제 Ajax
	@PostMapping("/auth/delete")
	@ResponseBody
	public Map<String, Object> resultAuthDeleteAjax(@RequestBody Map<String, Object> map) {

		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			
			ResultAuthEntity resultAuthEntity = new ResultAuthEntity();
			
			int id = Integer.parseInt((String)map.get("id")); // 게시글 번호
		
			resultAuthEntity = resultAuthService.resultAuthSelect(id);

			// 아이디 체크 추가해야함
			
			resultAuthService.resultAuthDelete(id);
			
			result.put("status", "ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "fail");
		}
		return result;
	}
	
	// 점수 댓글
	@PostMapping("/auth/score")
	@ResponseBody
	public Map<String, Object> resultAuthScoreAjax(@RequestBody Map<String, Object> map) throws Exception {

		Map<String, Object> result = new HashMap<String, Object>();
		
		ResultAuthEntity resultAuthEntity = new ResultAuthEntity();

		try {
			int id = Integer.parseInt((String)map.get("id")); // 게시글 번호
			
			resultAuthEntity = resultAuthService.resultAuthSelect(id);
			
			result.put("status", "ok");
			result.put("entity", resultAuthEntity);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "fail");
		}
		
		return result;
	}
	
	// 인증 게시판 평가
	@PostMapping("/auth/scoreinsert")
	@ResponseBody
	public Map<String, Object> resultAuthScoreInsertAjax(@RequestBody Map<String, Object> map) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			if (roleCheck() == false) {
				result.put("status", "fail");
				return result;
			}
			
			ResultAuthEntity resultAuthEntity = new ResultAuthEntity();
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			
			int id = Integer.parseInt((String)map.get("id")); // 게시글 번호
			int score = Integer.parseInt((String)map.get("score")); // 인증 평가 점수
			String comment = (String) map.get("comment");
			
			System.out.println("log : " + map);
			
			resultAuthEntity = resultAuthService.resultAuthSelect(id);
			
			resultAuthEntity.setResultAuthScore(score);
			resultAuthEntity.setCommentDate(timestamp);
			resultAuthEntity.setResultAuthComment(comment);
			
			resultAuthService.resultAuthInsert(resultAuthEntity);
			teamService.updatePoint(resultAuthEntity);
			
			
			result.put("status", "ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "fail");
		}
		return result;
	}
	
	// 인증 게시판 수정 폼
	@GetMapping("/auth/modify/{no}")
	public String studyModifyForm(@PathVariable("no") int id, Model model) {

		/**
		 * 게시글 수정 아이디 체크 넣기
		 */

		try {
			ResultAuthEntity resultAuthEntity = new ResultAuthEntity();

			resultAuthEntity = resultAuthService.resultAuthSelect(id);

			model.addAttribute("resultAuthEntity", resultAuthEntity);

			return "/resultauth/resultauthmodify";
		} catch (Exception e) {
			return "/resultauth/resultauthlist";
		}
	}
	
	// 인증 게시판 수정
	@PostMapping("/auth/modifypro")
	public String studyModify(ResultAuthEntity resultAuthEntity) {
		try {
			int num = resultAuthEntity.getResultAuthNum();
			
			resultAuthService.resultAuthInsert(resultAuthEntity);
			
			return "redirect:/auth/detail/" + num;
		} catch (Exception e) {
			return "/study";
		}
	}
	
	public boolean roleCheck() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	    if (authentication != null && authentication.isAuthenticated()) {
	        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

	        boolean isAdmin = authorities.stream()
	                .anyMatch(authority -> "ROLE_ADMIN".equals(authority.getAuthority()));

	        System.out.println("log : " + isAdmin);
	        if (isAdmin) {
	        	return true;
	        } else {
	        	return false;
	        }
	    }
	    return false;
	}
}

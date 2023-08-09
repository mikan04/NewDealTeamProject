package com.studycafe.team.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studycafe.member.auth.PrincipalDetails;
import com.studycafe.member.entity.MemberEntity;
import com.studycafe.member.service.MemberService;
import com.studycafe.team.entity.TeamEntity;
import com.studycafe.team.service.TeamService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TeamController {

	@Autowired
	private TeamService teamService;

	@Autowired
	private MemberService memberService;

	@GetMapping("/myteam/{teamNumber}")
	public String myTeam(@PathVariable("teamNumber") long teamNumber, Model model) {
		List<MemberEntity> member = memberService.getMyTeamMember(teamService.getMyTeam(teamNumber));
		log.info("MEMBER : {}", member);
		TeamEntity team = teamService.getMyTeam(teamNumber);
		log.info("team : {}", team);
		model.addAttribute("teamMember", member);
		model.addAttribute("team", team);

		return "/team/myTeam";

	}

	@GetMapping("/team/teamNameRegis")
	public String teamNameRegistPage() {
		return "/team/teamNameRegis";
	}

	@GetMapping("/team/teamMemberRegis")
	public String teamMemberRegistPage() {
		return "/team/teamMemberRegis";
	}

	@PostMapping("/team/nameCheck")
	@ResponseBody
	public boolean teamNameCheck(@RequestParam("teamName") String name) {
		boolean nameCheck = teamService.findTeamByName(name);
		return nameCheck;
	}

	@PostMapping("/team/getoutteam")
	@ResponseBody
	public boolean getOutTeam(@RequestParam("teamNumber") long teamNumber, @RequestParam("username") String username) {

		try {
			boolean member = memberService.getOutTeam(username, teamService.getMyTeam(teamNumber));
			log.info("member : {}", member);
			return member;		
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@PostMapping("/team/delete")
	@ResponseBody
	public boolean deleteTeam(@RequestParam("teamNumber") long teamNumber, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		boolean result = false;
		if (principalDetails == null) {
			throw new AccessDeniedException("회원만 팀 신청을 할 수 있습니다.");
		}
		
		String loginUser = principalDetails.getUsername();
		MemberEntity mem = memberService.findUsername(loginUser);
		TeamEntity myTeam = teamService.getMyTeam(teamNumber);
		if (mem.getTeamNumber() == null) {
			throw new AccessDeniedException("소속된 팀이 없습니다.");
		} else if(myTeam == null) {
			throw new AccessDeniedException("팀 정보를 찾을 수 없습니다.");
		}
		
		try {
			// 팀 삭제
			teamService.deleteTeam(teamNumber);
			// 멤버에서 팀 정보 삭제
			List<MemberEntity> members = memberService.getMyTeamMember(myTeam);			
			for(MemberEntity member: members) {
				result = memberService.getOutTeam(member.getUsername(), myTeam);
			}
			return result;		
		} catch (Exception e) {
			e.printStackTrace();
			
			return false;
		}
	}
	
	@PostMapping("/team/rankingchart")
	@ResponseBody
	public List<TeamEntity> getRanking() {
		
		return teamService.getRanking();
	}

}

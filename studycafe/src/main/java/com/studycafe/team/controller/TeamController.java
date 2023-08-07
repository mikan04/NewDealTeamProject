package com.studycafe.team.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
	public String myTeam(@PathVariable("teamNumber") int teamNumber, Model model) {
		TeamEntity teamEntity = new TeamEntity();
		teamEntity.setTeamNumber(teamNumber);
		List<MemberEntity> member = memberService.getMyTeamMember(teamEntity);
		log.info("MEMBER : {}",member);
		model.addAttribute("member",member);
		
		return "/team/myTeam";
	
	}
}

package com.studycafe.team.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studycafe.team.service.TeamService;

@Controller
public class TeamController {
	
	@Autowired
	private TeamService teamService;
	
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
	
	
	

}

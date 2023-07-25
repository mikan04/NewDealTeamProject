package com.studycafe.admin.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.studycafe.member.service.MemberService;
import com.studycafe.study.entity.StudyEntity;
import com.studycafe.study.service.StudyService;
import com.studycafe.team.entity.TeamEntity;
import com.studycafe.team.service.TeamService;

@Controller
public class AdminController {

	@Autowired
	private StudyService studyService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private TeamService teamService;


	// 관리자 메인 페이지
	@GetMapping("/admin/home")
	public String adminDashboard() {
		return "/admin/adminDashboard";
	}

	// 관리자 팀 관리 페이지
	@GetMapping("/admin/team")
	public String adminTeam() {
		return "/admin/adminTeam";
	}

	// 관리자 스터디 관리 페이지
	@GetMapping("/admin/study")
	public String adminStudy() {
		return "/admin/adminStudy";
	}

	// 관리자 유저 관리 페이지
	@GetMapping("/admin/user")
	public String adminUser() {
		
		return "/admin/adminUser";
	}

	// 관리자 랭킹 페이지
	@GetMapping("/admin/ranking")
	public String adminRanking() {
		return "/admin/adminRanking";
	}

	// 관리자 차트 페이지
	@GetMapping("/admin/chart")
	public String adminChart() {
		return "/admin/adminChart";
	}
	
	@GetMapping("/admin/api/dashboard")
	@ResponseBody
	public String getDashboardData() {
		JSONObject collector = new JSONObject();
		
		JSONArray topTeamList = new JSONArray();
		List<TeamEntity> topteams = teamService.getTopTeamByPoint();
		for(int i =0; i< topteams.size(); i++) {
			JSONObject team = new JSONObject();
			team.put("name", topteams.get(i).getTeamName());
			team.put("point", topteams.get(i).getPoint());
			topTeamList.add(team);	
		}
		
		JSONArray topApproveList = new JSONArray();
		List<TeamEntity> topApproves = teamService.getTopTeamByApproveCount();
		for(int i =0; i< topApproves.size(); i++) {
			JSONObject team = new JSONObject();
			team.put("name", topApproves.get(i).getTeamName());
			team.put("point", topApproves.get(i).getPoint());
			topTeamList.add(team);	
		}
		
		collector.put("team_point_rank", "dkdh");
		
		
		return collector.toJSONString();
	}

}

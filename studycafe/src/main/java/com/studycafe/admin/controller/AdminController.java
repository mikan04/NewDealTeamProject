package com.studycafe.admin.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.studycafe.member.dto.MemberSafeDto;
import com.studycafe.member.service.MemberService;
import com.studycafe.study.dto.StudyByMonthDto;
import com.studycafe.study.entity.StudyEntity;
import com.studycafe.study.service.StudyService;
import com.studycafe.team.dto.TeamMonthCountDto;
import com.studycafe.team.dto.TopTeamDto;
import com.studycafe.team.entity.TeamEntity;
import com.studycafe.team.repository.TeamRepository;
import com.studycafe.team.service.TeamService;

import lombok.extern.slf4j.Slf4j;

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
		ObjectMapper objectMapper = new ObjectMapper();

		List<TopTeamDto> topteams = teamService.getTopTeamByPoint();
		List<TopTeamDto> topApproves = teamService.getTopTeamByApproveCount();
		List<TeamMonthCountDto> newTeam = teamService.getNewTeamByMonth();
		List<StudyByMonthDto> studyByMonth = studyService.getStudyByMonth();

		try {
			collector.put("topTeamList", objectMapper.writeValueAsString(topteams));
			collector.put("topApproveList", objectMapper.writeValueAsString(topApproves));
			collector.put("newTeamByMonth", objectMapper.writeValueAsString(newTeam));
			collector.put("studyByMonth", objectMapper.writeValueAsString(studyByMonth));

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return collector.toJSONString();
	}

	@GetMapping("/admin/api/team")
	@ResponseBody
	public String getTeamData() {
		JSONObject collector = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
		objectMapper.registerModule(new JavaTimeModule());

		List<TopTeamDto> topteams = teamService.getTopTeamByPoint();
		List<TopTeamDto> topApproves = teamService.getTopTeamByApproveCount();
		List<TeamEntity> allTeams = teamService.getAllTeam();
		List<TeamEntity> notApproveTeam = teamService.getNotApprovedTeam();

		try {
			collector.put("topTeamList", objectMapper.writeValueAsString(topteams));
			collector.put("topApproveList", objectMapper.writeValueAsString(topApproves));
			collector.put("allTeams", objectMapper.writeValueAsString(allTeams));
			collector.put("notApproveTeam", objectMapper.writeValueAsString(notApproveTeam));

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return collector.toJSONString();
	}

	@PostMapping("/admin/api/team/approve")
	@ResponseBody
	public String approveTeam(@RequestBody HashMap<String, Object> map) {
		try {
			System.out.println(map.get("teamNum").toString());
			teamService.approveTeam(Long.parseLong(map.get("teamNum").toString()));
			return "Success";
		} catch (Exception e) {
			// TODO: handle exception
			return e.getMessage();
		}
	}

	@PostMapping("/admin/api/team/disapprove")
	@ResponseBody
	public String disapproveTeam(@RequestBody HashMap<String, Object> map) {
		try {
			System.out.println(map.get("teamNum").toString());
			teamService.disapproveTeam(Long.parseLong(map.get("teamNum").toString()));
			return "Success";
		} catch (Exception e) {
			// TODO: handle exception
			return e.getMessage();
		}
	}

	@GetMapping("/admin/api/study")
	@ResponseBody
	public String getStudyData() {
		JSONObject collector = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
		objectMapper.registerModule(new JavaTimeModule());

		Integer studyReserve = studyService.getStudyReserve();
		Integer studyDone = studyService.getStudyDone();
		List<StudyEntity> allStudies = studyService.getAllStudy();

		LocalDateTime time = null;

		int current = LocalDateTime.now().getHour();
		if (current >= 8 && current < 11) {
			time = LocalDate.now().atTime(8, 0);
		} else if (current >= 11 && current < 14) {
			time = LocalDate.now().atTime(11, 0);
		} else if (current >= 14 && current < 17) {
			time = LocalDate.now().atTime(14, 0);
		} else if (current >= 17 && current < 20) {
			time = LocalDate.now().atTime(17, 0);
		} else if (current >= 20 && current < 23) {
			time = LocalDate.now().atTime(20, 0);
		}

		Integer studyProg = studyService.getStudyProg(time);

		collector.put("studyReserve", studyReserve);
		collector.put("studyProg", studyProg);
		collector.put("studyDone", studyDone);

		try {
			collector.put("allStudies", objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(allStudies));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return collector.toJSONString();
	}

	@GetMapping("/admin/api/user")
	@ResponseBody
	public String getUserData() {
		JSONObject collector = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));

		Integer newUserCount = memberService.getNewMemberCount();
		List<MemberSafeDto> allMember = memberService.getAllMember();

		collector.put("newUserCount", newUserCount);
		try {
			collector.put("allMember", objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(allMember));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return collector.toJSONString();
	}
}

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
import com.studycafe.chatroom.entity.ChatRoomEntity;
import com.studycafe.chatroom.service.ChatRoomService;
import com.studycafe.chatroom.service.ChatRoomServiceImpl;
import com.studycafe.member.dto.MemberSafeDto;
import com.studycafe.member.entity.MemberEntity;
import com.studycafe.member.entity.Role;
import com.studycafe.member.service.MemberService;
import com.studycafe.study.dto.StudyByMonthDto;
import com.studycafe.study.entity.StudyEntity;
import com.studycafe.study.service.StudyService;
import com.studycafe.team.controller.TeamController;
import com.studycafe.team.dto.TeamMonthCountDto;
import com.studycafe.team.dto.TopTeamDto;
import com.studycafe.team.entity.TeamEntity;
import com.studycafe.team.repository.TeamRepository;
import com.studycafe.team.service.TeamService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AdminController {

	@Autowired
	private StudyService studyService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private TeamService teamService;
	@Autowired
	private ChatRoomService roomService;

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
		List<TeamMonthCountDto> teamByMonth = teamService.getTeamByMonth();
		List<StudyByMonthDto> studyByMonth = studyService.getStudyByMonth();

		try {
			collector.put("topTeamList", objectMapper.writeValueAsString(topteams));
			collector.put("topApproveList", objectMapper.writeValueAsString(topApproves));
			collector.put("teamByMonth", objectMapper.writeValueAsString(teamByMonth));
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
			Long teamNum = Long.parseLong(map.get("teamNum").toString());
			String teamName = map.get("teamName").toString();
			
			
			if (teamNum != null && teamName != null && !teamName.trim().equals("")) {
				teamService.approveTeam(teamNum);
		
				ChatRoomEntity room = new ChatRoomEntity();
				room.setRoomName(teamName+"룸");
				room.setTeamEntity(teamService.findTeamById(teamNum));
				roomService.addChatRoom(room);
				log.info("룸 추가");
			}
			
			return "Success";
		} catch (Exception e) {
			// TODO: handle exception
			return e.getMessage();
		}
	}

	@PostMapping("/admin/api/team/disapprove")
	@ResponseBody
	public boolean disapproveTeam(@RequestBody HashMap<String, Object> map) {
		try {
			boolean result = false;
			Long teamNum = Long.parseLong(map.get("teamNum").toString());
			if (teamNum != null) {
				// 팀 승인 해제
				teamService.disapproveTeam(teamNum);
			}
			// 채팅룸 삭제
			ChatRoomEntity chatRoom = roomService.findRoom(teamNum);
			if( chatRoom != null && chatRoom.getRoomIdx() != null) {
				roomService.deleteChatRoom(chatRoom.getRoomIdx());
				log.info("채팅룸 삭제 {}", chatRoom.getRoomIdx());
			}
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@GetMapping("/admin/api/study")
	@ResponseBody
	public String getStudyData() {
		JSONObject collector = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));

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
			collector.put("allStudies", objectMapper.writeValueAsString(allStudies));
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
	
	// 멤버 롤 업데이트
	@PostMapping("/admin/api/member/updateRole")
	@ResponseBody
	public boolean updateRole(@RequestBody HashMap<String, Object> map) {
		boolean result = false;
		String username = map.get("username").toString();
		String role = map.get("role").toString();
		log.info("Role의 값은 :{}", role);
		switch (role) {
		case "ROLE_ADMIN":
			System.out.println("ROLE_ADMIN");
			result = memberService.updateRole(username, Role.ROLE_ADMIN);
			break;
		case "ROLE_MANAGER":
			System.out.println("ROLE_MANAGER");
			result = memberService.updateRole(username, Role.ROLE_MANAGER);
			break;
		case "ROLE_MENTOR":
			System.out.println("ROLE_MENTOR");
			result = memberService.updateRole(username, Role.ROLE_MENTOR);
			break;
		case "ROLE_MEMBER":
			System.out.println("ROLE_MEMBER");
			result = memberService.updateRole(username, Role.ROLE_MEMBER);
			break;
		default:
			break;
		}
		return result;
		
	}
}

package com.studycafe.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

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

}

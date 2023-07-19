package com.studycafe.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.studycafe.study.entity.StudyEntity;
import com.studycafe.study.service.StudyService;

@Controller
public class StudyController {
	
	/**
	 * @author 홍정수
	 */
	
	@Autowired
	private StudyService studyService;
	
	// 스터디 모집 게시판 리스트
	@GetMapping("/study")
	public String studyList() {
		
		return "/study/studylist";
	}
	
	// 스터디 모집 게시물 등록 폼
	@GetMapping("/studyregistration")
	public String studyRegis() {
		return "/study/studyregistration";
	}
	
	// 스터디 모집 게시물 등록
	@PostMapping("/studyregistrationpro")
	public String studyRegisInsert(StudyEntity studyEntity) {
		System.out.println(studyEntity);
		
		studyService.studyRegisInsert(studyEntity); // 게시글 저장
		return "/study/studyregistration";
	}
	
	// 위도, 경도 불러오기
	@GetMapping("/studydetail/{no}")
	public String studyregisSelect(@PathVariable("no") int id, Model model) {
		
		StudyEntity studyEntity = new StudyEntity();
		
		studyEntity = studyService.studyRegisSelect(id);
		
		System.out.println(studyService.studyRegisSelect(id));
		
		model.addAttribute("studyEntity", studyEntity);
		
		return "/study/studydetail";
	}
}

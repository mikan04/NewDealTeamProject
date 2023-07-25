package com.studycafe.study.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.studycafe.study.entity.StudyEntity;
import com.studycafe.study.service.StudyService;

@Controller
public class StudyController {
	
	/**
	 * @author 홍정수
	 */
	
	@Autowired
	private StudyService studyService;
	
	// 스터디 모집 게시판 폼
	@GetMapping("/study")
	public String studyList() {
		return "/study/studylist";
	}
	
	// 스터디 모집 게시판 리스트 호출
	@GetMapping("/studyAjax")
	@ResponseBody
	public Page<StudyEntity> studyListAjax(@PageableDefault(page = 0, size = 10, sort = "studyNum", direction = Sort.Direction.DESC) Pageable pageable,
			@RequestParam(required = false) String keyword) {
		
		Page<StudyEntity> list = null;

		System.out.println("keyword : " + keyword);
		System.out.println("page : " + pageable.getPageNumber());
		// pageable.getPageNumber()

		if (keyword == null) {
			list = studyService.studyList(pageable);
		} else {
			list = studyService.studySearchList(keyword, pageable);
		}
		
		int nowPage = list.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage -4, 1);
		int endPage = Math.min(nowPage + 5,  list.getTotalPages());

		return list;
	}

	// 스터디 모집 게시물 등록 폼
	@GetMapping("/studyregistration")
	public String studyRegis() {
		return "/study/studyregistration";
	}
	
	// 스터디 모집 게시물 등록
	@PostMapping("/studyregistrationpro")
	public String studyInsert(StudyEntity studyEntity) {
		System.out.println(studyEntity);
		
		studyService.studyInsert(studyEntity); // 게시글 저장
		return "redirect:/study";
	}
	
	// 스터디 모집 게시물 열람
	@GetMapping("/studydetail/{no}")
	public String studySelect(@PathVariable("no") int id, Model model) {
		
		StudyEntity studyEntity = new StudyEntity();
		
		studyEntity = studyService.studySelect(id);

		model.addAttribute("studyEntity", studyEntity);
		
		return "/study/studydetail";
	}
	
	
	@GetMapping("/studyTime")
	@ResponseBody
	public String studySelectTimeByMap(@RequestParam("lat") int lat, @RequestParam("long") int lon,@RequestParam("date") String date) {
		LocalDate localDate = LocalDate.parse(date);
		
		List<StudyEntity> studyEntity = studyService.studySelectByMap(lat, lon, localDate);
		
		JSONArray times = new JSONArray();
		for(int i=0; i> studyEntity.size(); i++) {
			
			LocalDateTime reserveTime = studyEntity.get(0).getReserveTime();
			JSONObject time = new JSONObject();
			time.put("time", reserveTime);
			times.add(time);
		}
		return times.toJSONString();
	}
}

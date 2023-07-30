package com.studycafe.qna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studycafe.qna.entity.QnaEntity;
import com.studycafe.qna.service.QnaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@RequestMapping("/qna")
	public String qnaList() {
		return "/qna/qnaList";
	}
	
	//qna게시판
	@GetMapping("/qnalist")
	@ResponseBody
	public Page<QnaEntity> qnaListAjax(@PageableDefault(page = 0, size = 10, sort = "qnaNum", direction = Sort.Direction.DESC) Pageable pageable,
			@RequestParam(required = false) String keyword) {
		
		Page<QnaEntity> list = null;

		if (keyword == null) {
			list = qnaService.qnaList(pageable);
		} else {
			list = qnaService.qnaSearchList(keyword, pageable);
		}
		
	
		int nowPage = list.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage -4, 1);
		int endPage = Math.min(nowPage + 5,  list.getTotalPages());

		return list;
	}
	
	//qna등록폼
	@GetMapping("/qnaRegister")
	public String qnaRegister() {
		return "/qna/qnaRegister";
	}
	
	@PostMapping("/qnaRegisterPro")
	public String studyInsert(QnaEntity qnaEntity) {

		qnaService.qnaRegister(qnaEntity); // 게시글 저장
			return "redirect:/qna";
	}

}

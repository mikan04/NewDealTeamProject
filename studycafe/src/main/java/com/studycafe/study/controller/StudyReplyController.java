package com.studycafe.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.studycafe.study.service.StudyReplyService;

@Controller
public class StudyReplyController {

	/**
	 * @author 홍정수
	 */
	
	@Autowired
	private StudyReplyService studyReplyService;
	
	// 스터디 모집 댓글 Ajax
	public String studyReply() {
		return "";
	}
}

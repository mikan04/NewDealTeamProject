package com.studycafe.study.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studycafe.study.entity.StudyEntity;
import com.studycafe.study.entity.StudyReplyEntity;
import com.studycafe.study.service.StudyReplyService;

@Controller
public class StudyReplyController {

	/**
	 * @author 홍정수
	 */
	
	@Autowired
	private StudyReplyService studyReplyService;
	
	// 스터디 모집 댓글 Ajax
	@PostMapping("/studyReplyInsert")
	@ResponseBody
	public Map<String, Object> studyReplyInsert(@RequestBody StudyReplyEntity studyReplyEntity) {

		Map<String, Object> result = new HashMap<String, Object>();

		try {
			StudyEntity studyEntity = null;
			studyEntity = studyReplyEntity.getStudyEntity(); // StudyEntity 객체 생성

			int depth = studyReplyEntity.getStudyReplyDepth(); // 댓글 깊이 값 저장
			
			if (depth == 0) {  // ref 값이 0이면 일반 댓글
				// 부모 게시글 번호 생성
				int count = studyReplyService.StudyReplyCount(studyEntity.getStudyNum());
				studyReplyEntity.setStudyReplyRef(count); // 부모 게시글 번호 저장
			}
			
			studyReplyService.StudyReplyInsert(studyReplyEntity); // 댓글 작성
			
			result.put("status", "ok");
			
			return result;
		} catch(Exception e) {
			e.printStackTrace();
			result.put("status", "fail");
			
			return result;
		}
	}
	
	// 스터디 댓글 삭제
	@PostMapping("/studyReplyDelete")
	@ResponseBody
	public Map<String, Object> studyReplyDelete(@RequestBody StudyReplyEntity studyReplyEntity) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {

			Long id = (Long)studyReplyEntity.getStudyReplyNum();
			
			studyReplyService.StudyReplyDelete(id);
			
			result.put("status", "ok");
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "fail");
			
			return result;
		}
	}
	
	// 스터디 모집 댓글 Ajax
	@PostMapping("/studyReplyModify")
	@ResponseBody
	public Map<String, Object> studyReplyModify(@RequestBody StudyReplyEntity studyReplyEntity) {

		Map<String, Object> result = new HashMap<String, Object>();

		
		System.out.println("studyReplyEntity : " + studyReplyEntity);
		
		try {
			studyReplyService.StudyReplyInsert(studyReplyEntity); // 댓글 작성
			
			result.put("status", "ok");
			
			return result;
		} catch(Exception e) {
			e.printStackTrace();
			result.put("status", "fail");
			
			return result;
		}
	}
	
	// 스터디 댓글 리스트
	@PostMapping("/studyReplyList")
	@ResponseBody
	public List<StudyReplyEntity> studyReplyList(@RequestBody StudyReplyEntity studyReplyEntity) {
		
		List<StudyReplyEntity> list = null;
		StudyEntity studyEntity = null;
		
		studyEntity = studyReplyEntity.getStudyEntity();
		
		int studyNum = studyEntity.getStudyNum();

		list = studyReplyService.StudyReplyList(studyNum);
		
		return list;
	}
}

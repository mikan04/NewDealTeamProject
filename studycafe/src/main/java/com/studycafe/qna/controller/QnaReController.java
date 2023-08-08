package com.studycafe.qna.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studycafe.qna.entity.QnaEntity;
import com.studycafe.qna.entity.QnaReEntity;
import com.studycafe.qna.service.QnaReService;
import com.studycafe.study.entity.StudyReplyEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class QnaReController {
	
	@Autowired
	private QnaReService qnaReSer;
	
	@PostMapping("/qnaReplyInsert")
	@ResponseBody
	public Map<String, Object> qnaReInsert(@RequestBody QnaReEntity qnaReEntity) {

		Map<String, Object> result = new HashMap<String, Object>();

		try {
			QnaEntity qnaEntity = null;
			qnaEntity = qnaReEntity.getQnaEntity(); // StudyEntity 객체 생성

			int depth = qnaReEntity.getQnaReplyDepth(); // 댓글 깊이 값 저장
			
			if (depth == 0) {  // ref 값이 0이면 일반 댓글
				// 부모 게시글 번호 생성
				long count = qnaReSer.qnaReCount(qnaEntity.getQnaNum());
				qnaReEntity.setQnaReplyRef(count); // 부모 게시글 번호 저장
			}
			
			qnaReSer.qnaReInsert(qnaReEntity); // 댓글 작성
			
			result.put("status", "ok");
			
			return result;
		} catch(Exception e) {
			e.printStackTrace();
			result.put("status", "fail");
			
			return result;
		}
	}
	
	@PostMapping("/qnaReplyList")
	@ResponseBody
	public List<QnaReEntity> qnaReList(@RequestBody QnaReEntity qnaReEntity) {
		
		List<QnaReEntity> list = null;
		QnaEntity qnaEntity = null;
		
		qnaEntity = qnaReEntity.getQnaEntity();
		
		long qnaNum = qnaEntity.getQnaNum();

		list = qnaReSer.qnaReList(qnaNum);
		log.info("list : {}",list);
		return list;
	}
	
	@PostMapping("/qnaReplyDelete")
	@ResponseBody
	public Map<String, Object> qnaReDelete(@RequestBody QnaReEntity qnaReEntity) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {

			Long qnaRelyNum = (Long)qnaReEntity.getQnaReplyNum();
			
			qnaReSer.qnaReDelete(qnaRelyNum);
			
			result.put("status", "ok");
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "fail");
			
			return result;
		}
	}
	
	@PostMapping("/qnaReplyModify")
	@ResponseBody
	public Map<String, Object> studyReplyModify(@RequestBody QnaReEntity qnaReEntity) {

		Map<String, Object> result = new HashMap<String, Object>();

		
		System.out.println("qnaReEntity??????? : " + qnaReEntity);

		
		try {
			qnaReSer.qnaReInsert(qnaReEntity); // 댓글 작성
			
			result.put("status", "ok");
			
			return result;
		} catch(Exception e) {
			e.printStackTrace();
			result.put("status", "fail");
			
			return result;
		}
	}

}

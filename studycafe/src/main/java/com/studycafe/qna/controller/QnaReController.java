package com.studycafe.qna.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studycafe.cs.entity.CsEntity;
import com.studycafe.qna.entity.QnaEntity;
import com.studycafe.qna.entity.QnaReEntity;
import com.studycafe.qna.service.QnaReService;
import com.studycafe.study.entity.StudyEntity;
import com.studycafe.study.entity.StudyReplyEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class QnaReController {

	@Autowired
	private QnaReService qnaReplyService;

	// 스터디 모집 댓글 Ajax
	@PostMapping("/qnaReplyInsert")
	@ResponseBody
	public Map<String, Object> qnaReplyInsert(@RequestBody QnaReEntity qnaReEntity) {

		Map<String, Object> result = new HashMap<String, Object>();

		try {
			QnaEntity qnaEntity = null;
			qnaEntity = qnaReEntity.getQnaEntity(); // StudyEntity 객체 생성

			int depth = qnaReEntity.getQnaReplyDepth(); // 댓글 깊이 값 저장

			if (depth == 0) { // ref 값이 0이면 일반 댓글
				// 부모 게시글 번호 생성
				int count = (int) qnaReplyService.qnaReCount(qnaEntity.getQnaNum());
				log.info("count:{}",count);
				qnaReEntity.setQnaReplyRef(count); // 부모 게시글 번호 저장
			}

			qnaReplyService.qnaReInsert(qnaReEntity); // 댓글 작성

			result.put("status", "ok");

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "fail");

			return result;
		}
	}

	// 스터디 댓글 삭제
	@PostMapping("/qnaReplyDelete")
	@ResponseBody
	public Map<String, Object> qnaReplyDelete(@RequestBody QnaReEntity qnaReEntity) {

		Map<String, Object> result = new HashMap<String, Object>();

		try {

			Long id = (Long) qnaReEntity.getQnaReplyNum();

			qnaReplyService.qnaReDelete(id);

			result.put("status", "ok");

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "fail");

			return result;
		}
	}

	// 스터디 모집 댓글 Ajax
	@PostMapping("/qnaReplyModify")
	@ResponseBody
	public Map<String, Object> studyReplyModify(@RequestBody QnaReEntity qnaReEntity) {

		Map<String, Object> result = new HashMap<String, Object>();

		System.out.println("QnaReEntity : " + qnaReEntity);

		try {
			qnaReplyService.qnaReInsert(qnaReEntity); // 댓글 작성

			result.put("status", "ok");

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "fail");

			return result;
		}
	}

	// 스터디 댓글 리스트
	@PostMapping("/qnaReplyList")
	@ResponseBody
	public List<QnaReEntity> studyReplyList(@RequestBody QnaReEntity qnaReEntity) {

		List<QnaReEntity> list = null;
		QnaEntity qnaEntity = null;

		qnaEntity = qnaReEntity.getQnaEntity();

		int qnaNum = (int) qnaEntity.getQnaNum();

		list = qnaReplyService.qnaReList(qnaNum);

		return list;
	}
}

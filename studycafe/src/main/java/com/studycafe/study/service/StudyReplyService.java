package com.studycafe.study.service;

import java.util.List;

import com.studycafe.study.entity.StudyEntity;
import com.studycafe.study.entity.StudyReplyEntity;

public interface StudyReplyService {

	public void StudyReplyInsert(StudyReplyEntity studyReplyEntity); // 댓글 입력
	public List<StudyReplyEntity> StudyReplyList(int studyNum); // 댓글 리스트
	public int StudyReplyCount(int id); // 댓글 카운트
	public void StudyReplyDelete(Long id); // 댓글 삭제
}

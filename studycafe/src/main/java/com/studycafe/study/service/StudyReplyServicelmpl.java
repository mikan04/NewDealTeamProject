package com.studycafe.study.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studycafe.study.entity.StudyEntity;
import com.studycafe.study.entity.StudyReplyEntity;
import com.studycafe.study.repository.StudyReplyRepository;

@Service("studyReplyService")
public class StudyReplyServicelmpl implements StudyReplyService {

	@Autowired
	private StudyReplyRepository studyReplyRepository;
	
	@Override
	public void StudyReplyInsert(StudyReplyEntity studyReplyEntity) {
		// TODO Auto-generated method stub
		studyReplyRepository.save(studyReplyEntity);
	}

	@Override
	public List<StudyReplyEntity> StudyReplyList(int studyNum) {
		// TODO Auto-generated method stub
		return studyReplyRepository.findByStudyEntity(studyNum);
	}

	@Override
	public int StudyReplyCount(int id) {
		// TODO Auto-generated method stub
		return studyReplyRepository.replycount(id);
	}

	@Override
	public void StudyReplyDelete(Long id) {
		// TODO Auto-generated method stub
		studyReplyRepository.deleteById(id);		
	}	
}
